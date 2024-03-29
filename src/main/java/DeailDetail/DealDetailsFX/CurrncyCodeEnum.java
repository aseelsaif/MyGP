package DeailDetail.DealDetailsFX;
import com.google.common.collect.Maps;
import java.util.Map;

public enum CurrncyCodeEnum {
	AFN,
	ALL,
	DZD,
	USD,
	EUR,
	AOA,
	XCD,
	ARS,
	AMD,
	AWG,
	AUD,
	AZM,
	BSD,
	BHD,
	BDT,
	BBD,
	BYR,
	BZD,
	XOF,
	BMD,
	INR,
	BTN,
	BOB,
	BOV,
	BAM,
	BWP,
	NOK,
	BRL,
	BND,
	BGN,
	BIF,
	KHR,
	XAF,
	CAD,
	CVE,
	KYD,
	CLP,
	CLF,
	CNY,
	COP,
	COU,
	KMF,
	CDF,
	NZD,
	CRC,
	HRK,
	CUP,
	CYP,
	CZK,
	DKK,
	DJF,
	DOP,
	EGP,
	SVC,
	ERN,
	EEK,
	ETB,
	FKP,
	FJD,
	XPF,
	GMD,
	GEL,
	GHC,
	GIP,
	GTQ,
	GNF,
	GWP,
	GYD,
	HTG,
	HNL,
	HKD,
	HUF,
	ISK,
	IDR,
	IRR,
	IQD,
	ILS,
	JMD,
	JPY,
	JOD,
	KZT,
	KES,
	KPW,
	KRW,
	KWD,
	KGS,
	LAK,
	LVL,
	LBP,
	ZAR,
	LSL,
	LRD,
	LYD,
	CHF,
	LTL,
	MOP,
	MKD,
	MGA,
	MGF,
	MWK,
	MYR,
	MVR,
	MTL,
	MRO,
	MUR,
	MXN,
	MXV,
	MDL,
	MNT,
	MAD,
	MZM,
	MMK,
	NAD,
	NPR,
	ANG,
	NIO,
	NGN,
	OMR,
	PKR,
	PAB,
	PGK,
	PYG,
	PEN,
	PHP,
	PLN,
	QAR,
	ROL,
	RUR,
	RUB,
	RWF,
	SHP,
	WST,
	STD,
	SAR,
	CSD,
	SCR,
	SLL,
	SGD,
	SKK,
	SIT,
	SBD,
	SOS,
	LKR,
	SDD,
	SRD,
	SZL,
	SEK,
	SYP,
	TWD,
	TJS,
	TZS,
	THB,
	TOP,
	TTD,
	TND,
	TRL,
	TMM,
	UGX,
	UAH,
	AED,
	GBP,
	USS,
	USN,
	UYU,
	UZS,
	VUV,
	VEB,
	VND,
	YER,
	ZMK,
	ZWD;

	public void checkCurrency() {
	for (CurrncyCodeEnum currency : CurrncyCodeEnum.values()) { 
	    System.out.println(currency); 
	}
	}
	private static final Map<String, CurrncyCodeEnum> nameIndex = 
			Maps.newHashMapWithExpectedSize(CurrncyCodeEnum.values().length);
	static {
	    for (CurrncyCodeEnum currency : CurrncyCodeEnum.values()) {
	        nameIndex.put(currency.name(), currency);
	    }
	}
	public static CurrncyCodeEnum lookupByName(String name) {
	    return nameIndex.get(name);
	}
}
