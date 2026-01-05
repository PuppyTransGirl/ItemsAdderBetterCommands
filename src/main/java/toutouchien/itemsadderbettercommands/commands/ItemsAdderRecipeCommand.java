package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderRecipeCommand {
    private ItemsAdderRecipeCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("recipe")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.user.iarecipe"))
                .then(Commands.argument("item", ArgumentTypes.key())
                        .suggests((ctx, builder) -> {
                            for (CustomStack stack : ItemsAdder.getAllItems()) {
                                builder.suggest(stack.getNamespacedID());
                            }

                            return builder.buildFuture();
                        })
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            Key itemKey = ctx.getArgument("item", Key.class);

                            Bukkit.dispatchCommand(sender, "iarecipe " + itemKey.asString());

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
