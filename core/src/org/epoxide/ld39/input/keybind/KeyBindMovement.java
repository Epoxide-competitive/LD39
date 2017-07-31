package org.epoxide.ld39.input.keybind;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.util.Direction;
import org.epoxide.ld39.world.World;

public class KeyBindMovement extends KeyBindMulti {

    private final Direction direction;

    public KeyBindMovement (Direction direction, int... keycodes) {

        super(keycodes);
        this.direction = direction;
    }

    @Override
    public void onUpdate (float delta) {

        final EntityPlayer player = LD39.instance.getEntityPlayer();
        final World world = LD39.instance.getWorld();

        boolean moved = false;
        final float prevX = player.x;
        final float prevY = player.y;

        if (player.getMovementDelay() > 0) {

            player.setMovementDelay(player.getMovementDelay() - 1);
        }

        else {

            if (this.direction == Direction.UP) {
                if (world.getTileState((int) player.x, (int) player.y + 1).tile.isCollidable()) {
                    if (player.y + 1 > world.getMapHeight() - 1) {
                        player.y = world.getMapHeight() - 1;
                    }
                    else {
                        player.y++;
                    }
                    moved = true;
                }
            }
            else if (this.direction == Direction.DOWN) {
                if (world.getTileState((int) player.x, (int) player.y - 1).tile.isCollidable()) {
                    if (player.y - 1 < 0) {
                        player.y = 0;
                    }
                    else {
                        player.y--;
                    }
                    moved = true;
                }
            }
            else if (this.direction == Direction.LEFT) {
                if (world.getTileState((int) player.x - 1, (int) player.y).tile.isCollidable()) {
                    if (player.x - 1 < 0) {
                        player.x = 0;
                    }
                    else {
                        player.x--;
                    }
                    moved = true;
                }
            }
            else if (this.direction == Direction.RIGHT) {
                if (world.getTileState((int) player.x + 1, (int) player.y).tile.isCollidable()) {
                    if (player.x + 1 > world.getMapWidth() - 1) {
                        player.x = world.getMapWidth() - 1;
                    }
                    else {
                        player.x++;
                    }
                    moved = true;
                }
            }
            if (moved) {
                if (player.power > 0) {
                    player.power--;
                }
                else {
                    player.power = 0;
                    player.x = prevX;
                    player.y = prevY;
                }

                player.resetMovementDelay();
            }
        }
    }
}