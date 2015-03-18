package io.shparki.vholt.util;

public class Time {
	
	public static final long SECOND = 1_000_000_000L;
	public static long getTime() { return System.nanoTime(); }
	
	private static long delta;
	private static long updateDelta;
	private static long renderDelta;
	
	public static long getDelta() { return delta; }
	public static void setDelta(long delta) { Time.delta = delta; }
	public static long getUpdateDelta() { return updateDelta; }
	public static void setUpdateDelta(long updateDelta) { Time.updateDelta = updateDelta; }
	public static long getRenderDelta() { return renderDelta; }
	public static void setRenderDelta(long renderDelta) { Time.renderDelta = renderDelta; }
	
	
}
