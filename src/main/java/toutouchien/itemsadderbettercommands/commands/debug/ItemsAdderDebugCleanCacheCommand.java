package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugCleanCacheCommand {
    private ItemsAdderDebugCleanCacheCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("cleancache")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iacleancache"))
                .then(Commands.literal("blocks")
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            Bukkit.dispatchCommand(sender, "iacleancache blocks");

                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("items")
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            Bukkit.dispatchCommand(sender, "iacleancache items");

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
