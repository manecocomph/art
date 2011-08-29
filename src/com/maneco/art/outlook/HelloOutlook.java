package com.maneco.art.outlook;

import com.moyosoft.connector.com.ComponentObjectModelException;
import com.moyosoft.connector.exception.LibraryNotFoundException;
import com.moyosoft.connector.ms.outlook.Outlook;
import com.moyosoft.connector.ms.outlook.folder.FoldersCollection;
import com.moyosoft.connector.ms.outlook.recipient.OutlookRecipient;

public class HelloOutlook
{
	public static void main(String[] args)
	{
		try
		{
			// Outlook application
			Outlook outlookApplication = new Outlook();

			OutlookRecipient currentUser = outlookApplication.getCurrentUser();

			System.out.println("Hello " + currentUser.getName() + " !");
			System.out.println(
				"Your e-mail address is " + currentUser.getAddress() + ".");
			
			FoldersCollection fc = outlookApplication.getFolders();
			FoldersCollection sfc = null;
			
			if (!fc.isEmpty()) {
				for (int i = 0; i < fc.size(); i++) {
					System.out.println(fc.get(i).getName());
					sfc = fc.get(i).getFolders();
					for (int j = 0; j < sfc.size(); j++) {
						System.out.println("\t" + sfc.get(j).getName());
					}
					
				}
			}

			// Dispose the library
			outlookApplication.dispose();
		}
		catch (ComponentObjectModelException ex)
		{
			System.out.println("COM error has occured: ");
			ex.printStackTrace();
		}
		catch (LibraryNotFoundException ex)
		{
			// If this error occurs, verify the file 'moyocore.dll' is present
			// in java.library.path
			System.out.println("The Java Outlook Library hasn't been found.");
			ex.printStackTrace();
		}
	}
}