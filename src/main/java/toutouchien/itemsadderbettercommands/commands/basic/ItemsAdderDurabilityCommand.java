package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDurabilityCommand {
    private ItemsAdderDurabilityCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("durability")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iarepair", true))
                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            int amount = ctx.getArgument("amount", Integer.class);

                            Bukkit.dispatchCommand(executor, "iadurability " + amount);

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
