package webfunctions;

import java.util.ArrayList;
import java.util.List;

import objects.RequestStatus;
import objects.RequestType;

public class Authenticate {

	/* check if amount requested is valid */

	public static double validateAmount(String amountString) throws Exception {

		double amount;
		try {
			amount = Double.valueOf(amountString);
			if (amount > 0) {
				int decimalPoint = amountString.indexOf('.');
				int numDecimals = amountString.length() - decimalPoint - 1;
				if (numDecimals > 2) {
					throw new Exception();
				}
			}
			return amount;
		} catch (NumberFormatException e) {
			throw new Exception();
		}
	}

	/*  */

	public static RequestType validateType(List<RequestType> list, String typeSelected) throws Exception {

		int typeId = 0;
		String typeName = null;
		for (RequestType type : list) {
			System.out.println("Type:::: " + type.getType() + " Type select: " + typeSelected);
			if (type.getType_id() == (Integer.parseInt(typeSelected))) {
				typeId = type.getType_id();
				typeName = type.getType();
			}
		}
		if (typeId == 0)
			throw new Exception();
		return new RequestType(typeId, typeName);
	}

	public static List<RequestType> getTypeList() {
		List<RequestType> list = new ArrayList<RequestType>();
		list.add(new RequestType(1, "Housing"));
		list.add(new RequestType(2, "Travel"));
		list.add(new RequestType(3, "Food"));
		list.add(new RequestType(4, "Other"));
		return list;

	}

	public static List<RequestStatus> getStatusList() {
		List<RequestStatus> list = new ArrayList<RequestStatus>();
		list.add(new RequestStatus(1, "Pendin"));
		list.add(new RequestStatus(2, "Approved"));
		list.add(new RequestStatus(3, "Denied"));
		return list;

	}

	/*  */

	public static RequestStatus setReimbstatus(List<RequestStatus> list, String statusSelected) throws Exception {
		System.out.println("Reached reimb status");
		int statusId = 1;
		System.out.println(new RequestStatus(statusId, statusSelected));
		return new RequestStatus(statusId, statusSelected);
	}

	/**
	 * public static ReimbStatus validateStatus(List<ReimbStatus> list, String
	 * statusSelected) throws Exception{ System.out.println("Reached reimb status");
	 * int statusId= 1; for(ReimbStatus status: list){
	 * System.out.println("Status:::: " + status.getStatus());
	 * if(status.getStatus_id()==(Integer.parseInt(statusSelected))){ statusId =
	 * status.getStatus_id(); } } System.out.println("statusSelected"
	 * +statusSelected); if(statusId == 0) throw new Exception();
	 * System.out.println("new ReimbStatus(statusId, statusSelected" + new
	 * ReimbStatus(statusId, statusSelected)); return new ReimbStatus(statusId,
	 * statusSelected); }
	 **/
}