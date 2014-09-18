package util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveManager {	
	
	public static final double WAVE_DELAY = 1.0; //seconds
	
	private List<Wave> mWaves;
	private int mWaveCount;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private Random mRand;
	
	public WaveManager(){
		mWaves = new ArrayList<Wave>();
		mWaveCount = 0;
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		mRand = new Random();
		addWave();
	}
	
	private void addWave(){
		mWaveCount++;
		Wave tmpWave = new Wave();
		populateWave(tmpWave);
		mWaves.add(tmpWave);
	}
	
	private void populateWave(Wave wave){
		createWavePop(wave, Wave.mZombieType.FATTY);
		createWavePop(wave, Wave.mZombieType.RUNNER);
		createWavePop(wave, Wave.mZombieType.WALKER);
	}
	
	private void createWavePop(Wave wave, Wave.mZombieType zType){		
		for(int i = 0; i < mWaveCount; i++){
			wave.addZombie(mRand.nextInt((40 - 0) + 1) + 0, mRand.nextInt((140 - 100) + 1) + 100, zType); //fix location generation
		}
	}
	
	public void update(long timeNS){
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		System.out.println(mTimePassed);
		
		if(mTimePassed > WAVE_DELAY){
			addWave();
			mTimePassed -= WAVE_DELAY;
		}
		
		for(Wave w:mWaves){
			w.update(timeNS);
		}
	}
	
	public void paint(Graphics g){
		for(Wave w:mWaves){
			w.paint(g);
		}
	}
	
	public Wave getWave(int index){
		return mWaves.get(index);
	}
}
