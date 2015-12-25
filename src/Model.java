
public class Model {
	
	
	
	
	private int menhir = 0;
	private boolean geantAnimation = false ; 
	private boolean farfadetAnimation = false;
	
	
	private static Model instance;

	private Model()
	{
		
	}
	
	public int getMenhir() {
		return menhir;
	}

	public void setMenhir(int menhir) {
		this.menhir = menhir;
	}
	
	public static Model getInstance()
	{
		
		if (instance == null)
		{
			instance = new Model();
		}
		return instance;
		
	}

	public boolean isGeantAnimation() {
		return geantAnimation;
	}
	
	

	public void setGeantAnimation(boolean geantAnimation) {
		this.geantAnimation = geantAnimation;
	}

	public boolean isFarfadetAnimation() {
		return farfadetAnimation;
	}

	public void setFarfadetAnimation(boolean farfadetAnimation) {
		this.farfadetAnimation = farfadetAnimation;
	}
	
	
	
	
}
