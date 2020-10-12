public CurrencyUnits{
	private String currency;
	private Int amount;
	
	public CurrencyUnits(string currency,int amount){
		this.currency = currency;
		this.amount = amount;
	}
	
	public string getCurrency(){
		return currency;
	}
	
	public int getAmount(){
		return amount;
	}
}

public class CurrencyController{

	@postmapping(value=/totalcurrencies method = RequestMethod.post,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE
	public @ResponseBody JsonResponse getTotalAmountbyCurrency(@RequestBody CurrencyUnits [] curencyUnits){
		Map<String,int> currencyMap = new HashMap();
		
		for(int i =0; i<currencyUnits.length;i++){
				if(currencyMap.get(curencyUnits[i].currency == null){
					currencyMap.put(curencyUnits[i].currency, curencyUnits[i].amount);
				}
				else{
					int amount = curencyUnits[i].amount;
					currencyMap.put(curencyUnits[i].currency, curencyUnits[i].amount+amount);
				}
		}
		return new JSONObject(currencyMap);
	}
} 
