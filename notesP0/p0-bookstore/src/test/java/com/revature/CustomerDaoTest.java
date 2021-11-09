package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
//import com.revature.repositories.CustomerList;
import com.revature.repositories.CustomerPostgres;

public class CustomerDaoTest {

	private static CustomerDao cd;
	private int id;
	private String name;
	private String username;
	private String password;
	private int cardNumber;
	private boolean isEmployee;
	private Customer owner;

	@BeforeEach
	public void setup() {
		cd = new CustomerPostgres();
//		cd.edit(new Customer(1, "name", "user", "pass", "ogarlette0@bravesites.com", "3578903365519546", false, null));
	}

	@Test
	public void getAllCheck() {
		List<Customer> expected = new ArrayList<Customer>();
		expected.add(new Customer(1, "name", "user", "pass", "ogarlette0@bravesites.com", "3578903365519546", false,
				new Customer(1)));
		expected.add(new Customer(2, "Laurella", "customer", "pass", "lhaggar1@opera.com", "3536516899323010", false,
				new Customer(1)));
		expected.add(new Customer(3, "Byron", "employee", "pass", "bfludder2@ihg.com", "3557554178295439", false,
				new Customer(1)));
		expected.add(new Customer(4, "Emlen", "elachaize3", "Lachaize", "elachaize3@live.com", "5602234579897063",
				false, new Customer(1)));
		expected.add(new Customer(5, "Sheridan", "stefft4", "Tefft", "stefft4@bizjournals.com", "6304155415360090",
				false, new Customer(1)));
		expected.add(new Customer(6, "Linnell", "lroget5", "Roget", "lroget5@theguardian.com", "3545122936738721",
				false, new Customer(1)));
		expected.add(new Customer(7, "Ragnar", "rslides6", "Slides", "rslides6@behance.net", "67630061008462029", false,
				new Customer(1)));
		expected.add(new Customer(8, "Shepperd", "sthebeau7", "Thebeau", "sthebeau7@weibo.com", "560223150571617023",
				false, new Customer(1)));
		expected.add(new Customer(9, "Olly", "obrammall8", "Brammall", "obrammall8@oracle.com", "3538677009278722",
				false, new Customer(1)));
		expected.add(new Customer(10, "Nannette", "ncrookes9", "Crookes", "ncrookes9@upenn.edu", "3564133507364330",
				false, new Customer(1)));
		expected.add(
				new Customer(11, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, new Customer(0)));
		List<Customer> actual = cd.getAll();
		assertEquals(expected, actual);
	}

	@Test
	public void addValid() {
		List<Customer> expected = new ArrayList<Customer>();
		expected.add(new Customer(1, "name", "user", "pass", "ogarlette0@bravesites.com", "3578903365519546", false,
				new Customer(1)));
		expected.add(new Customer(2, "Laurella", "customer", "pass", "lhaggar1@opera.com", "3536516899323010", false,
				new Customer(1)));
		expected.add(new Customer(3, "Byron", "employee", "pass", "bfludder2@ihg.com", "3557554178295439", false,
				new Customer(1)));

		expected.add(new Customer(4, "Emlen", "elachaize3", "Lachaize", "elachaize3@live.com", "5602234579897063",
				false, new Customer(1)));
		expected.add(new Customer(5, "Sheridan", "stefft4", "Tefft", "stefft4@bizjournals.com", "6304155415360090",
				false, new Customer(1)));
		expected.add(new Customer(6, "Linnell", "lroget5", "Roget", "lroget5@theguardian.com", "3545122936738721",
				false, new Customer(1)));
		expected.add(new Customer(7, "Ragnar", "rslides6", "Slides", "rslides6@behance.net", "67630061008462029", false,
				new Customer(1)));
		expected.add(new Customer(8, "Shepperd", "sthebeau7", "Thebeau", "sthebeau7@weibo.com", "560223150571617023",
				false, new Customer(1)));
		expected.add(new Customer(9, "Olly", "obrammall8", "Brammall", "obrammall8@oracle.com", "3538677009278722",
				false, new Customer(1)));
		expected.add(new Customer(10, "Nannette", "ncrookes9", "Crookes", "ncrookes9@upenn.edu", "3564133507364330",
				false, new Customer(1)));
		expected.add(
				new Customer(11, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, new Customer(0)));
		cd.add(new Customer(11, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, null));
		List<Customer> actual = cd.getAll();
		assertEquals(expected, actual);
	}

	@Test
	public void getByIdValid() {
		Customer expected = new Customer(2, "Laurella", "customer", "pass", "lhaggar1@opera.com", "3536516899323010",
				false, new Customer(1));
		Customer actual = cd.getById(2);
		assertEquals(expected, actual);
	}

	@Test
	public void getByIdValidTwo() {
		Customer expected = new Customer(1, "name", "user", "pass", "ogarlette0@bravesites.com", "3578903365519546",
				false, new Customer(1));
		Customer actual = cd.getById(1);
		assertEquals(expected, actual);
	}

//	@Test
//	public void editIfTrue() {
//		Customer expected = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, new Customer(1));
//		Customer c = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, new Customer(1));
//		cd.edit(c);
//		Customer actual = cd.getById(1);
//		assertEquals(expected.getName(), actual.getName());
//	}

	@Test
	public void deleteCustomerIfTrue() {
		List<Customer> expected = new ArrayList<Customer>();
		expected.add(new Customer(1, "name", "user", "pass", "ogarlette0@bravesites.com", "3578903365519546", false,
				new Customer(1)));
		expected.add(new Customer(2, "Laurella", "customer", "pass", "lhaggar1@opera.com", "3536516899323010", false,
				new Customer(1)));
		expected.add(new Customer(3, "Byron", "employee", "pass", "bfludder2@ihg.com", "3557554178295439", false,
				new Customer(1)));
		expected.add(new Customer(4, "Emlen", "elachaize3", "Lachaize", "elachaize3@live.com", "5602234579897063",
				false, new Customer(1)));
		expected.add(new Customer(5, "Sheridan", "stefft4", "Tefft", "stefft4@bizjournals.com", "6304155415360090",
				false, new Customer(1)));
		expected.add(new Customer(6, "Linnell", "lroget5", "Roget", "lroget5@theguardian.com", "3545122936738721",
				false, new Customer(1)));
		expected.add(new Customer(7, "Ragnar", "rslides6", "Slides", "rslides6@behance.net", "67630061008462029", false,
				new Customer(1)));
		expected.add(new Customer(8, "Shepperd", "sthebeau7", "Thebeau", "sthebeau7@weibo.com", "560223150571617023",
				false, new Customer(1)));
		expected.add(new Customer(9, "Olly", "obrammall8", "Brammall", "obrammall8@oracle.com", "3538677009278722",
				false, new Customer(1)));
		expected.add(new Customer(10, "Nannette", "ncrookes9", "Crookes", "ncrookes9@upenn.edu", "3564133507364330",
				false, new Customer(1)));
		cd.deleteById(11);
		List<Customer> actual = cd.getAll();
		assertEquals(expected, actual);
	}

//	private static CustomerDao cd;
//	private int id;
//	private String name;
//	private String username;
//	private String password;
//	private int cardNumber;
//	private boolean isEmployee;
//	private Customer owner;
//
//	@BeforeEach
//	public void setup() {
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//		customers.add(new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false,  null));
//		customers.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		customers.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false, null));
//		cd = new CustomerList(customers);
//	}
//
//	@Test
//	public void getAllCheck() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(
//				new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false, null));
//		expected.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		expected.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false,  null));
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void addValid() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(
//				new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false, null));
//		expected.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		expected.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false, null));
//		expected.add(new Customer(3, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, null));
//		cd.add(new Customer(3, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, null));
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByIdValid() {
//		Customer expected = new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false,
//				null);
//		Customer actual = cd.getById(2);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByIdValidTwo() {
//		Customer expected = new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null);
//		Customer actual = cd.getById(1);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void editIfTrue() {
//		Customer expected = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, null);
//		Customer c = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, null);
//		cd.edit(c);
//		Customer actual = cd.getById(1);
//		assertEquals(expected.getName(), actual.getName());
//	}

//	@Test
//	public void deleteCustomerIfTrue() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
//		cd.deleteById(0);
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}	

}
