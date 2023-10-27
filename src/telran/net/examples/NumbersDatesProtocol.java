package telran.net.examples;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import telran.net.ApplProtocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class NumbersDatesProtocol implements ApplProtocol {

	@Override
	public Response getResponse(Request request) {
		String requestType = request.requestType();
		Response response = null;
		double[] numbers = null;
		Serializable requestData = request.requestData();
		Serializable responseData = null;
		try {
			if(requestType.contains("numbers")) {
				numbers = (double[])requestData ;
			}
			responseData = switch(requestType) {
			case "numbers/add" -> numbers[0] + numbers[1];
			case "numbers/subtract" -> numbers[0] - numbers[1];
			case "numbers/divide" -> numbers[0] / numbers[1];
			case "numbers/multiply" -> numbers[0] * numbers[1];
			case "dates/days" -> dates_days(requestData);
			case "dates/between" -> date_between(requestData);
			default -> null;
			};
			response = responseData == null ? new Response(ResponseCode.WRONG_TYPE, requestType) : new Response(ResponseCode.OK, responseData);
			
		} catch (Exception e) {
			response = new Response(ResponseCode.WRONG_DATA, e.getMessage());
		}
		
		return response;
	}
	private Serializable date_between(Serializable requestData) {
		LocalDate[] dates = (LocalDate[]) requestData;
		return ChronoUnit.DAYS.between(dates[0], dates[1]);
	}
	Serializable dates_days(Serializable requestData) {
		DateDays dateDays = (DateDays) requestData;
		LocalDate date = dateDays.date();
		int days = dateDays.days();
		return date.plusDays(days);
		
	}


}
