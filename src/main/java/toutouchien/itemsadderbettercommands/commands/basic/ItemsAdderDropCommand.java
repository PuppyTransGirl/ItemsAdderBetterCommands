package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.math.BlockPosition;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDropCommand {
    private ItemsAdderDropCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("drop")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iadrop"))
                .then(Commands.argument("position", ArgumentTypes.blockPosition())
                        .then(Commands.argument("world", ArgumentTypes.world()).then(getAfter()))
                )
                .then(Commands.argument("player", ArgumentTypes.player()).then(getAfter()))
                .build();
    }

    private static RequiredArgumentBuilder<CommandSourceStack, Key> getAfter() {
        return Commands.argument("item", ArgumentTypes.key())
                .suggests((ctx, builder) -> {
                    for (CustomStack stack : ItemsAdder.getAllItems()) {
                        builder.suggest(stack.getNamespacedID());
                    }

                    return builder.buildFuture();
                })
                .executes(ctx -> {
                    CommandSender sender = CommandUtils.sender(ctx);

                    Key itemIDAsKey = ctx.getArgument("item", Key.class);
                    dropItem(sender, locationFromContext(ctx), itemIDAsKey, 1);

                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);

                            Key itemIDAsKey = ctx.getArgument("item", Key.class);
                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                            dropItem(sender, locationFromContext(ctx), itemIDAsKey, amount);

                            return Command.SINGLE_SUCCESS;
                        })
                );
    }

    private static Location locationFromContext(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        try {
            BlockPositionResolver resolver = ctx.getArgument("position", BlockPositionResolver.class);
            BlockPosition blockPosition = resolver.resolve(ctx.getSource());

            World world = ctx.getArgument("world", World.class);
            return blockPosition.toLocation(world);
        } catch (IllegalArgumentException e) {
            PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
            Player target = targetResolver.resolve(ctx.getSource()).getFirst();
            return target.getLocation();
        }
    }

    private static void dropItem(CommandSender sender, Location location, Key itemIDAsKey, int amount) {
        String itemID = itemIDAsKey.asString();

        Bukkit.dispatchCommand(sender, "iadrop %s %s %s %s %s %s".formatted(
                itemID,
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getWorld().getName(),
                amount
        ));
    }
}
