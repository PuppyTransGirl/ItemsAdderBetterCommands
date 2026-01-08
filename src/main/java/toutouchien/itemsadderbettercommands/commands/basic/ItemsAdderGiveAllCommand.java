package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderGiveAllCommand {
    private ItemsAdderGiveAllCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("giveall")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iagiveall"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("item", StringArgumentType.greedyString())
                                .suggests((ctx, builder) -> {
                                    for (CustomStack stack : ItemsAdder.getAllItems()) {
                                        builder.suggest(stack.getNamespacedID());
                                    }

                                    return builder.buildFuture();
                                })
                                .executes(ctx -> {
                                    CommandSender sender = CommandUtils.sender(ctx);

                                    PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                    List<Player> players = targetResolver.resolve(ctx.getSource());
                                    String itemID = ctx.getArgument("item", String.class);

                                    giveItem(sender, players, itemID, 1, false);

                                    return Command.SINGLE_SUCCESS;
                                })
                                .then(
                                        Commands.argument("amount", IntegerArgumentType.integer(1))
                                                .executes(ctx -> {
                                                    CommandSender sender = CommandUtils.sender(ctx);

                                                    PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                                    List<Player> players = targetResolver.resolve(ctx.getSource());
                                                    String itemID = ctx.getArgument("item", String.class);
                                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                                    giveItem(sender, players, itemID, amount, false);

                                                    return Command.SINGLE_SUCCESS;
                                                })
                                                .then(Commands.literal("silent")
                                                        .executes(ctx -> {
                                                            CommandSender sender = CommandUtils.sender(ctx);

                                                            PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                                            List<Player> players = targetResolver.resolve(ctx.getSource());
                                                            String itemID = ctx.getArgument("item", String.class);
                                                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                                            giveItem(sender, players, itemID, amount, true);

                                                            return Command.SINGLE_SUCCESS;
                                                        }))
                                )))
                .build();
    }

    private static void giveItem(CommandSender sender, List<Player> players, String itemID, int amount, boolean silent) {
        List<CustomStack> validItems = ItemsAdder.getAllItems().stream()
                .filter(stack -> stack.getNamespacedID().contains(itemID))
                .toList();

        for (CustomStack stack : validItems) {
            for (Player player : players) {
                Bukkit.dispatchCommand(sender, "iagive %s %s %d %s".formatted(
                        player.getName(),
                        stack.getNamespacedID(),
                        amount,
                        silent ? "silent" : ""
                ));
            }
        }
    }
}
