package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.math.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugLiquidCommand {
    private ItemsAdderDebugLiquidCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("liquid")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.ialiquid", true))
                .then(Commands.argument("position", ArgumentTypes.blockPosition())
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();

                            BlockPositionResolver resolver = ctx.getArgument("position", BlockPositionResolver.class);
                            BlockPosition blockPosition = resolver.resolve(ctx.getSource());

                            Bukkit.dispatchCommand(executor, "ialiquid %d %d %d".formatted(
                                    blockPosition.blockX(),
                                    blockPosition.blockY(),
                                    blockPosition.blockZ()
                            ));

                            return Command.SINGLE_SUCCESS;
                        }))
                .executes(ctx -> {
                    Entity executor = ctx.getSource().getExecutor();
                    Bukkit.dispatchCommand(executor, "ialiquid");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
