
// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import Demo.*;

public class Client {
	private static Scanner teclado;
	private static InetAddress address;
	private static Ice.Communicator communicator;

	public static void main(String[] args) {
		int status = 0;
		java.util.List<String> extraArgs = new java.util.ArrayList<>();

		try {
			address = InetAddress.getByName("champi.sytes.net");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			communicator = Ice.Util.initialize(args);
			if (!extraArgs.isEmpty()) {
				System.err.println("too many arguments");
				status = 1;
			} else {
				status = run(communicator);
			}
		} finally {
			if (communicator != null) {
				communicator.shutdown();
			}

		}

		System.exit(status);
	}

	private static int run(Ice.Communicator communicator) {
		teclado = new Scanner(System.in);
		Ice.ObjectPrx base = communicator.stringToProxy("dataCollector:ssl -h "+address.getHostAddress()+" -p 7052");
		DataCollectorPrx twoway = (DataCollectorPrx) DataCollectorPrxHelper.checkedCast(base).ice_twoway().ice_secure(true);

		if (twoway == null) {
			System.err.println("invalid proxy");
			return 1;
		}

		String option = null;

		System.out.println("Introducir alias de la maquina");
		String alias = teclado.nextLine();

		twoway.registerConnection(alias);

		while (!(option = menu()).equalsIgnoreCase("shutdown")) {
			twoway.saleDone(alias, option);
		}

		twoway.shutdown(alias);

		return 0;
	}

	private static String menu() {
		System.out.println("Insert product code (shutdown to end): ");
		return teclado.nextLine();

	}
}
