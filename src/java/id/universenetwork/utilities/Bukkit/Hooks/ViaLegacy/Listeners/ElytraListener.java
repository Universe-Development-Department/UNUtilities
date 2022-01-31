package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Listeners;

import org.bukkit.util.Vector;

public class ElytraListener implements org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler(priority = org.bukkit.event.EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
        org.bukkit.entity.Player p = e.getPlayer();
        if (!p.isGliding()) return;
        if (com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(p) > 76) return;
        org.bukkit.Location loc = p.getLocation();
        Vector velocity = p.getVelocity();
        Vector direction = loc.getDirection();
        double motionX = velocity.getX();
        double motionY = velocity.getY();
        double motionZ = velocity.getZ();
        float pitch = loc.getPitch() * 0.017453292F;
        double directionH = Math.sqrt(direction.getX() * direction.getX() + direction.getZ() * direction.getZ());
        double speedH = Math.sqrt(motionX * motionX + motionZ * motionZ);
        float speedV = (float) Math.cos(pitch);
        speedV = (float) (speedV * speedV * Math.min(1.0D, direction.length() / 0.4D));
        motionY += -0.08D + speedV * 0.06D;
        if ((motionY < 0.0D) && (directionH > 0.0D)) {
            double d2 = motionY * -0.1D * speedV;
            motionY += d2;
            motionX += direction.getX() * d2 / directionH;
            motionZ += direction.getZ() * d2 / directionH;
        }
        if (pitch < 0.0F) {
            double speed = speedH * -Math.sin(pitch) * 0.04D;
            motionY += speed * 3.2D;
            motionX -= direction.getX() * speed / directionH;
            motionZ -= direction.getZ() * speed / directionH;
        }
        if (directionH > 0.0D) {
            motionX += (direction.getX() / directionH * speedH - motionX) * 0.1D;
            motionZ += (direction.getZ() / directionH * speedH - motionZ) * 0.1D;
        }
        motionX *= 0.9900000095367432D;
        motionY *= 0.9800000190734863D;
        motionZ *= 0.9900000095367432D;
        velocity.setX(motionX);
        velocity.setY(motionY);
        velocity.setZ(motionZ);
        p.setVelocity(velocity);
    }
}