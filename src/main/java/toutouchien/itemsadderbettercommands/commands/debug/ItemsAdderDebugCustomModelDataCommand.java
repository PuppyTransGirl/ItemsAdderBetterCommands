package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

public class ItemsAdderDebugCustomModelDataCommand {
    private ItemsAdderDebugCustomModelDataCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("custommodeldata")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.custommodeldata"))
                .then(Commands.argument("item", ArgumentTypes.key())
                        .suggests((ctx, builder) -> {
                            for (CustomStack stack : ItemsAdder.getAllItems()) {
                                builder.suggest(stack.getNamespacedID());
                            }

                            return builder.buildFuture();
                        })
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Key itemKey = ctx.getArgument("item", Key.class);

                            Bukkit.dispatchCommand(executor, "iacustommodeldata " + itemKey.asString());

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
