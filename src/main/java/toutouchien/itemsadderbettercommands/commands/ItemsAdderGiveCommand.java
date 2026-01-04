package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

import java.util.List;

public class ItemsAdderGiveCommand {
    private ItemsAdderGiveCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("give")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iagive"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("item", ArgumentTypes.key())
                                .suggests((ctx, builder) -> {
                                    for (CustomStack stack : ItemsAdder.getAllItems()) {
                                        builder.suggest(stack.getNamespacedID());
                                    }

                                    return builder.buildFuture();
                                })
                                .executes(ctx -> {
                                    Player executor = (Player) ctx.getSource().getExecutor();

                                    PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                    List<Player> players = targetResolver.resolve(ctx.getSource());
                                    Key itemIDAsKey = ctx.getArgument("item", Key.class);

                                    giveItem(executor, players, itemIDAsKey, 1, false);

                                    return Command.SINGLE_SUCCESS;
                                })
                                .then(
                                        Commands.argument("amount", IntegerArgumentType.integer(1))
                                                .executes(ctx -> {
                                                    Player executor = (Player) ctx.getSource().getExecutor();

                                                    PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                                    List<Player> players = targetResolver.resolve(ctx.getSource());
                                                    Key itemIDAsKey = ctx.getArgument("item", Key.class);
                                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                                    giveItem(executor, players, itemIDAsKey, amount, false);

                                                    return Command.SINGLE_SUCCESS;
                                                })
                                                .then(Commands.literal("silent")
                                                        .executes(ctx -> {
                                                            Player executor = (Player) ctx.getSource().getExecutor();

                                                            PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
                                                            List<Player> players = targetResolver.resolve(ctx.getSource());
                                                            Key itemIDAsKey = ctx.getArgument("item", Key.class);
                                                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                                            giveItem(executor, players, itemIDAsKey, amount, true);

                                                            return Command.SINGLE_SUCCESS;
                                                        }))
                                )))
                .build();
    }

    private static void giveItem(Entity executor, List<Player> players, Key itemIDAsKey, int amount, boolean silent) {
        String itemID = itemIDAsKey.asString();
        CustomStack stack = CustomStack.getInstance(itemID);

        for (Player player : players) {
            if (stack == null) {
                Bukkit.dispatchCommand(executor, "iagive %s %s".formatted(player.getName(), itemID));
                return;
            }

            Bukkit.dispatchCommand(executor, "iagive %s %s %d %s".formatted(
                    player.getName(),
                    itemID,
                    amount,
                    silent ? "silent" : ""
            ));
        }
    }
}
