package de.hska.lat.behavior.information.filter;

/**
 * A bandpass filter that only passes signals of the
 * given frequency. If a lower or higher frequency is
 * found, the amplitude is set to 0.
 * 
 * Data is collected for the period of time in which one
 * signal is expected. Afterwards, the first derivative 
 * of the data is calculated to identify edges. If two
 * edges are found, the data is passed; otherwise the data
 * is attenuated to 0.
 * 
 * @author Julia Vogel
 *
 */
public class BandpassFilter extends InformationFilter
{
	
	private static final float[] OPERATOR = new float[] {-0.5f, 0.0f, 0.5f};	// local operator for first derivation
	private static final float THRESHOLD = 100.0f;								// minimum amplitude
		
	private int dataCount;
	private float[] derivedData;
	private float[] processedData;
	
	private float lastValue;
	private boolean tryAgain;
	private boolean attenuate;


	/**
	 * Creates an instance of a bandpass filter which
	 * only passes signals occurring at the given frequency.
	 * The filter's put method is expected to be called
	 * regularly at the given time base to be able to analyse
	 * the frequency.
	 * 
	 * @param frequency	The frequency that is to be passed by
	 * 					the filter. (Hz)
	 * @param timeBase	Data is expected to be put to the filter
	 * 					at any given period of time. (ms)
	 */
	public BandpassFilter(float frequency, int timeBase)
	{
		super(1);
		dataCount = (int) ((1.0f / frequency) / ((float) timeBase / 1000.0f));		
		data = new float[dataCount];
		derivedData = new float[dataCount];
		processedData = new float[dataCount];
		readPointer = 0;
		writePointer = 0;
		lastValue = 0.0f;
		tryAgain = true;
		attenuate = true;
	}

	
	private void calculateDerivedData()
	{
		for (int i = 0; i < dataCount; i++)
		{
			if (i == 0)
			{
				derivedData[i] = OPERATOR[0] * lastValue;
				derivedData[i] += OPERATOR[1] * data[i];
				derivedData[i] += OPERATOR[2] * data[i + 1];
			}
			else if (i == (dataCount - 1))
			{
				derivedData[i] = 0.0f;
			}
			else
			{
				derivedData[i] = OPERATOR[0] * data[i - 1];
				derivedData[i] += OPERATOR[1] * data[i];
				derivedData[i] += OPERATOR[2] * data[i + 1];
			}
			
			if (Math.abs(derivedData[i]) < THRESHOLD)
			{
				derivedData[i] = 0.0f;
			}
		}
	}
	
	
	private void processDerivedData()
	{
		int ascendCount = 0;
		int descendCount = 0;
		
		for (int i = 0; i < dataCount; i++)
		{
			if ((i > 0) && (derivedData[i] > 0) && (derivedData[i - 1] == 0))
			{
				ascendCount++;
			}
			else if ((i == 0) && (derivedData[i] > 0))
			{
				ascendCount++;
			}
			else if ((i > 0) && (derivedData[i] < 0) && (derivedData[i - 1] == 0))
			{
				descendCount++;
			}
			else if ((i == 0) && (derivedData[i] < 0))
			{
				descendCount++;
			}
		}
		
		if ((ascendCount == 1) && (descendCount == 1))
		{
			attenuate = false;
			tryAgain = true;
		}
		else if (tryAgain && !attenuate)
		{
			attenuate = false;
			tryAgain = false;
		}
		else
		{
			attenuate = true;
			tryAgain = true;
		}
		
		attenuate();

	}
	
	
	private void attenuate()
	{
		if (!attenuate)
		{
			processedData = data;
		}
		else
		{
			for (int i = 0; i < dataCount; i++)
			{
				processedData[i] = 0.0f;
			}
		}
	}
	
	
	@Override
	public synchronized void put(float value)
	{		
		this.value = processedData[writePointer];
		data[writePointer] = value;
		
		writePointer++;
		if (writePointer >= dataCount)
		{
			writePointer = 0;
			calculate();
			lastValue = value;
		}

	}


	@Override
	protected float calculate()
	{
		calculateDerivedData();
		processDerivedData();
		
		// Console output of the data (before, after, and derivative)
		/*System.out.print("processed: ");
		for (int i = 0; i < dataCount; i++)
		{
			System.out.print(String.format("%f ", processedData[i]));
		}
		System.out.println();
		
		System.out.print("data: ");
		System.out.print(String.format("%f ", lastValue));
		for (int i = 0; i < dataCount; i++)
		{
			System.out.print(String.format("%f ", data[i]));
		}
		System.out.print(" ");

		System.out.print("derived: ");
		for (int i = 0; i < dataCount; i++)
		{
			System.out.print(String.format("%f ", derivedData[i]));
		}
		System.out.println();*/

		return 0.0f;		
	}
	
}
