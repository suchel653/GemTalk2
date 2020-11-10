package prj.entity;

public class Bgm {

	private BgmListener bgmListener;
	
	public Bgm() {
		
	}

	public void play() {
		if (bgmListener != null)
			bgmListener.playLoseBgm();
	}
	
	public void setBgmListener(BgmListener bgmListener) {
		this.bgmListener = bgmListener;
	}
	
	
}
