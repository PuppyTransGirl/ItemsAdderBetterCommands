package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugSha1Command {
    private ItemsAdderDebugSha1Command() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("sha1")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.user.iasha1"))
                .then(Commands.literal("local")
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            Bukkit.dispatchCommand(sender, "iasha1 local");

                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("online")
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            Bukkit.dispatchCommand(sender, "iasha1 online");

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
