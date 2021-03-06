package waves;

import gameItems.tower.TowerManager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import player.Player;
import tiles.Level;
import util.GameSettings;

public class WaveManager 
{	
	private List<Wave> mWaves;
	private int mWaveCount;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private Level mLevel;
	private Player mPlayer;
	
	private int waveSize;
	
	public WaveManager(Level level, Player player)
	{
		mLevel = level;
		mWaves = new ArrayList<Wave>();
		mWaveCount = 0;
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		waveSize = 1;
		mPlayer = player;
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
	
	public Wave addTemplateWave()
	{
		mWaveCount++;
		Wave tmpWave = new Wave();
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
		createWaveZombies(wave, Wave.mZombieType.IMP);
	}
	
	private void createWaveZombies(Wave wave, Wave.mZombieType zType)
	{		
		for(int i = 0; i < waveSize; i++)
		{
			wave.addZombie(mLevel.getPathIndexed(0).getXPos() + (GameSettings.TILE_SIZE/2), 
					mLevel.getPathIndexed(0).getYPos() + (GameSettings.TILE_SIZE/2), 
					zType, 
					mPlayer,
					mLevel);
		}
	}
	
	public void update(long timeNS, TowerManager towerManager)
	{
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		
		if (mWaves.size() == 0 || mWaves.get(mWaves.size() - 1).getFinishedSpawning())
		{
			mTimePassed += mCurTime - mPrevTime;
		}
		
		if(mTimePassed > GameSettings.TIME_BETWEEN_WAVE)
		{
			addWave();
			mTimePassed -= GameSettings.TIME_BETWEEN_WAVE;
		}
		
		for(Wave w:mWaves)
		{
			w.update(timeNS, towerManager, mLevel);
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
