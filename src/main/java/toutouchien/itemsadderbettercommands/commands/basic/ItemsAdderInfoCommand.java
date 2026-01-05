package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderInfoCommand {
    private ItemsAdderInfoCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("info")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iainfo"))
                .executes(ctx -> {
                    CommandSender sender = CommandUtils.sender(ctx);
                    Bukkit.dispatchCommand(sender, "iainfo");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
