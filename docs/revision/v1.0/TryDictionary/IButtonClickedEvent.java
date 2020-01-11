package TryDictionary;


public interface IButtonClickedEvent{

	// when button clicked
	// it will gather information
	// and send to this function
	public void invoke(String[] list);
}