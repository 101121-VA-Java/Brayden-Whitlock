package com.revature.daos;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeHibernate implements EmployeeDao {

	/**
	 * Adds an employee to the database
	 * 
	 * @return the employee with the new id or null if no record is created
	 */
	@Override
	public Employee add(Employee e) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(e);
		tx.commit();
		s.close();
		return e;
	}

	/**
	 * Retrieves an employee by its id from the database
	 * 
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getById(int id) {
		Employee e = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			String hql = "from Employee where id = :employee_id";
			TypedQuery<Employee> tq = s.createQuery(hql, Employee.class);
			tq.setParameter("employee_id", id);
			e = tq.getSingleResult();
		}
		return e;
	}

	/**
	 * Retrieves an employee by its name from the database
	 * 
	 * @return the employee if found or null otherwise
	 */
	@Override
	public Employee getByName(String name) {
		// TODO Auto-generated method stub
		Employee e = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// instead of working with table name/column name, work with class and fields
			String hql = "from Employee where name = :name";
			TypedQuery<Employee> tq = s.createQuery(hql, Employee.class);
			tq.setParameter("name", name);
			e = tq.getSingleResult();
		}
		return e;
	}

	/**
	 * Retrieves all employees from the database
	 * 
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			employees = s.createQuery("from Employee", Employee.class).list();
		}
		return employees;
	}

	/**
	 * Retrieves employees by department id from the database
	 * 
	 * @return a List of Employee objects
	 */
	@Override
	public List<Employee> getEmployeesByDepartmentId(int deptId) {
		List<Employee> employees = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			Predicate predicateForDeptId = cb.equal(root.get("department"), deptId);
			cq.select(root).where(predicateForDeptId);
			employees = (List<Employee>) s.createQuery(cq).getResultList();
		}
		return employees;
		/*
		 * Use a Criteria
		 */
	}

	/**
	 * Updates an employee
	 */
	@Override
	public void update(Employee d) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.update(d);
			tx.commit();
		}
	}

	/**
	 * Deletes an employee
	 */
	@Override
	public void delete(Employee d) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(d);
			tx.commit();
		}
	}
}
