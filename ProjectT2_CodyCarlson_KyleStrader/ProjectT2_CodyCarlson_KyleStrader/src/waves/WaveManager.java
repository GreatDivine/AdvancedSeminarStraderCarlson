package waves;

import gameItems.tower.Tower;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameSettings;

public class WaveManager 
{	
	
	public static final double WAVE_DELAY = 1.0; //seconds
	
	private List<Wave> mWaves;
	private int mWaveCount;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private Random mRand;
	
	private int waveSize;
	
	public WaveManager()
	{
		mWaves = new ArrayList<Wave>();
		mWaveCount = 0;
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		waveSize = 1;
		mRand = new Random();
		addWave();
	}
	
	public Wave addWave()
	{
		mWaveCount++;
		Wave tmpWave = new Wave();
		populateWave(tmpWave);
		mWaves.add(tmpWave);
		
		if (mWaveCount % GameSettings.WAVES_BEFORE_INCREASE == 0)
		{
			waveSize++;
		}
		
		return tmpWave;
	}
	
	public void populateWave(Wave wave)
	{
		createWaveZombies(wave, Wave.mZombieType.FATTY);
		createWaveZombies(wave, Wave.mZombieType.RUNNER);
		createWaveZombies(wave, Wave.mZombieType.WALKER);
	}
	
	private void createWaveZombies(Wave wave, Wave.mZombieType zType)
	{		
		for(int i = 0; i < waveSize; i++)
		{
			wave.addZombie(mRand.nextInt((40 - 0) + 1) + 0, mRand.nextInt((140 - 100) + 1) + 100, zType); //fix location generation
		}
	}
	
	public void update(long timeNS, Tower tower)
	{
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > WAVE_DELAY)
		{
			addWave();
			mTimePassed -= WAVE_DELAY;
		}
		
		for(Wave w:mWaves)
		{
			w.update(timeNS, tower);
		}
	}
	
	public void paint(Graphics g)
	{
		for(Wave w:mWaves)
		{
			w.paint(g);
		}
	}
	
	public Wave getWave(int index)
	{
		return mWaves.get(index);
	}
	
	public int getNumWavesSpawned()
	{
		return mWaveCount;
	}
}
