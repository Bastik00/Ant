package de.hska.lat.robot.component.sensor.mlx90620;

public enum Mlx90620IrFrameRate {
	
	
	IR_FRAME_RATE_0(15, 0.5f),
	IR_FRAME_RATE_1(14, 1),
	IR_FRAME_RATE_2(13, 2),
	IR_FRAME_RATE_4(12, 4),
	IR_FRAME_RATE_8(11, 8),
	IR_FRAME_RATE_16(10, 16),
	IR_FRAME_RATE_32(9, 32),
	IR_FRAME_RATE_64(8, 64),
	IR_FRAME_RATE_128(7, 128),
	IR_FRAME_RATE_256(6, 256),
	IR_FRAME_RATE_512_5(5, 512),
	IR_FRAME_RATE_512_4(4, 512),
	IR_FRAME_RATE_512_3(3, 512),
	IR_FRAME_RATE_512_2(2, 512),
	IR_FRAME_RATE_512_1(1, 512),
	IR_FRAME_RATE_512(0, 512);
	
	
	private final int number;
	private final float frameRate;

	
	
	
	Mlx90620IrFrameRate(int number, float frameRate)
{
	this.number = number;
	this.frameRate = frameRate;
}

	
	
	
public int getNumber() 
{
	return (number);
}

public float getRate() 
{
	return (this.frameRate);
}
	
	


public String toString() 
{
	return (this.getRate()+"Hz");
};
	



}
