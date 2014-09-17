package util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveManager {
	
	private List<Wave> mWaves;
	private int mWaveCount;
	private double mWaveDelay;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private Random mRand;
	
	public WaveManager(double waveDel){
		mWaves = new ArrayList<Wave>();
		mWaveCount = 0;
		mWaveDelay = waveDel;
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
		
		/*switch(tmp){
		case 0:
			createWavePop(wave, Wave.mZombieType.WALKER);
			break;
		case 1:
			createWavePop(wave, Wave.mZombieType.RUNNER);
			createWavePop(wave, Wave.mZombieType.WALKER);
			break;
		case 2:
			createWavePop(wave, Wave.mZombieType.FATTY);
			createWavePop(wave, Wave.mZombieType.RUNNER);
			createWavePop(wave, Wave.mZombieType.WALKER);
			break;	
		}*/
		
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
		
		if(mTimePassed > mWaveDelay){
			addWave();
			mTimePassed -= mWaveDelay;
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
	
	public void setWaveDelay(double delay){
		mWaveDelay = delay;
	}
}
