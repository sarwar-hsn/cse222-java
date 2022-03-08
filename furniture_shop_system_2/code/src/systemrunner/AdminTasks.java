package systemrunner;

import java.util.Scanner;

import systemEmployee.Admin;
import systemEmployee.BranchEmployee;
import systemtools.Branch;
public class AdminTasks {
	
	
	/**
	 * function to run the tasks of administrator
	 * @param input takes a Scanner to read input
	 * @param admin takes a admin
	 */
	public void run(Scanner input,Admin admin) {
		int choice = 10;
		String branchName,name,surName;
		int employeeId,branchId;
		while(choice!=0) {
			try {
				options(admin);
				choice = input.nextInt();
				switch(choice) {
				case 0 :
					System.out.println("Logging out ...");
					return;
				case 1 :
					System.out.print("Please Enter the Branch Name : ");
					branchName = input.next();
					admin.addBranch(branchName);
					System.out.println("\n"+branchName+" added as new branch");
					break;
				case 2:
					admin.seeBranches();
					System.out.print("Please Enter the Branch Name to delete : ");
					branchName = input.next();
					Branch deletedBranch=  admin.deleteBranch(branchName);
					if(deletedBranch!=null)
					System.out.println("\n"+deletedBranch.getName()+" branch is removed");
					else System.out.println("\n couldn't find any branch by that name !!!");
					break;
				case 3 :
					admin.seeBranches();
					break;
				case 4:
					System.out.print("Please Enter Employee Name : ");
					name = input.next();
					System.out.print("Please Enter Employee Surname: ");
					surName = input.next();
					admin.seeBranches();
					System.out.print("Assign a branch to this employee(enter name/id of the branch) : ");
					branchName=input.next();
					boolean found = admin.addBranchEmployee(name, surName, branchName);
					boolean _found;
					if(!found) {
						try {
							_found = admin.addBranchEmployee(name, surName, Integer.parseInt(branchName));
						} catch (Exception e) {
							_found=false;
						}
						if(!found && !_found) System.out.println("\nInvalid Branch!!!\n");	
					}
					break;
					
				case 5:
					System.out.println("\n\t\t---Branch Employees---");
					if(admin.seeBranchEmployees()) {
						System.out.print("Please Enter 'Id' of employee you want to delete : ");
						employeeId = input.nextInt();
						System.out.print("Please Enter Employee's Branch Id : ");
						branchId = input.nextInt();
						BranchEmployee employee = admin.deleteBranchEmployee(branchId, employeeId);	
						if(employee!=null)System.out.println("\n"+employee.getName() +" is removed !!! \n");
						else System.out.println("\nCouldn't remove !!!\n");
					}
					break;
				case 6:
					System.out.println("\n\t\t---Branch Employees---");
					admin.seeBranchEmployees();
					break;
				case 7:
					admin.readMsg();
					break;
				default:
					System.out.println("\nInvalid Input !!!\n");
					break;
				}
			} catch (Exception e) {
				String badInput = input.next();
				System.out.println("\n"+badInput + " is not a valid input !!!\n");
				continue;
	
			}
			
		}
		
		
	}
	
	/**
	 * fucntion to display the options of a administrator
	 * @param admin takes a admin as parameter
	 */
	private  void options(Admin admin) {
		System.out.println("\t\t---Welcome "+admin.getName()+"---");
		System.out.println("1. Add Branch");
		System.out.println("2. Remove Branch");
		System.out.println("3. See Branches");
		System.out.println("4. Add Branch Employee");
		System.out.println("5. Remove Branch Employee");
		System.out.println("6. See Branch Employees");
		System.out.println("7. Check Messages");
		System.out.println("0. Logout\n");
		System.out.print("Enter your choice : ");
	}

}
