package util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.GameSettings;

public class WaveManager {

		private static final int NUM_ZOMBIE_TYPES = 3;
		private List<Wave> mWaves;
		private int mWaveCount;
		private int mWaveSet;
		private double mTimePassed;
		private double mCurTime;
		private double mPrevTime;
		private double mStartTime;
		
		public WaveManager(){
			mWaves = new ArrayList<Wave>();
			mWaveCount = 0;
			mWaveSet = 0;
			mTimePassed = 0;
			mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
			mCurTime = mStartTime;
			mPrevTime = 0;
			addWave();
		}
		
		private void addWave(){
			mWaveCount++;
			Wave tmpWave = new Wave();
			populateWave(tmpWave);
			mWaves.add(tmpWave);
		}
		
		private void populateWave(Wave wave){
			int tmp = mWaveCount % NUM_ZOMBIE_TYPES;
			mWaveSet = mWaveCount / NUM_ZOMBIE_TYPES;
			
			switch(tmp){
			case 1:
				createWavePop(wave, Wave.mZombieType.WALKER, mWaveSet);
				break;
			case 2:
				createWavePop(wave, Wave.mZombieType.RUNNER, mWaveSet);
				break;
			case 3:
				createWavePop(wave, Wave.mZombieType.FATTY, mWaveSet);
				break;
			}
		}
		
		private void createWavePop(Wave wave, Wave.mZombieType zType, int count){
			
			for(int i = 0; i < count; i++){
				wave.addZombie(20, 100, zType); //fix location generation
			}
		}
		
		public void update(long timeNS){
			mPrevTime = mCurTime;
			mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
			mTimePassed += (mCurTime - mPrevTime) - mStartTime;
			System.out.println(mTimePassed);
			
			if(mTimePassed > 1){
				addWave();
				mTimePassed -= 1;
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
}
