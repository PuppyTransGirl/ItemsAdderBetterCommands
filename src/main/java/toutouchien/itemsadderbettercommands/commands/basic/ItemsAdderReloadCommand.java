package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderReloadCommand {
    private ItemsAdderReloadCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("reload")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iazip"))
                .executes(ctx -> {
                    CommandSender sender = CommandUtils.sender(ctx);
                    Bukkit.dispatchCommand(sender, "iazip");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
