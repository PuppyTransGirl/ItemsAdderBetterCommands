package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugBlockCommand {
    private ItemsAdderDebugBlockCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("block")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iablock"))
                .executes(ctx -> {
                    CommandSender sender = CommandUtils.sender(ctx);
                    Bukkit.dispatchCommand(sender, "iablock");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
