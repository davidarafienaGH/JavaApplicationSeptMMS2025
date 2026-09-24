package hospital;

import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.Department;
import hospital.models.Nurse;
import hospital.models.NurseAssignment;
import hospital.models.Patient;
import hospital.models.Pharmacist;
import hospital.models.Room;
import hospital.models.User;
import hospital.models.Ward;
import hospital.models.Diagnosis;
import hospital.models.Invoice;
import hospital.models.InvoiceItem;
import hospital.models.Medication;
import hospital.models.Payment;
import hospital.models.Prescription;
import hospital.models.StaffRole;

import hospital.services.AdmissionServices;
import hospital.services.AppointmentService;
import hospital.services.BedService;
import hospital.services.BillingService;
import hospital.services.DepartmentService;
import hospital.services.PatientService;
import hospital.services.DoctorService;
import hospital.services.LaboratoryTechnicianService;
import hospital.services.NurseService;
import hospital.services.LaboratoryTestService;
import hospital.services.PharmacistService;
import hospital.services.RoomService;
import hospital.services.UserService;
import hospital.services.WardService;
import hospital.services.DiagnosisService;
import hospital.services.MedicalRecordService;
import hospital.services.MedicationService;
import hospital.services.PrescriptionService;
import hospital.services.TreatmentService;
import hospital.services.MedicationService;
import hospital.services.PaymentService;
import hospital.services.PrescriptionService;

import hospital.userview.AdmissionView;
import hospital.userview.AppointmentView;
import hospital.userview.BedView;
import hospital.userview.BillingView;
import hospital.userview.DiagnosisView;
import hospital.userview.PatientView;
import hospital.userview.DoctorView;
import hospital.userview.LaboratoryTestView;
import hospital.userview.LaboratoryTechnicianView;
import hospital.userview.MedicalRecordView;
import hospital.userview.MedicationView;
import hospital.userview.NurseView;
import hospital.userview.PharmacistView;
import hospital.userview.PrescriptionView;
import hospital.userview.RoomView;
import hospital.userview.WardView;
import hospital.userview.TreatmentView;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ABCHospitalApp {
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final AppointmentView appointmentView = new AppointmentView();
    private static final LaboratoryTechnicianService labTechService = new LaboratoryTechnicianService();
    private static final LaboratoryTechnicianView labTechView = new LaboratoryTechnicianView();
    private static PharmacistService pharmacistService = new PharmacistService();
    private static PharmacistView pharmacistView = new PharmacistView();
    private static final WardService wardService = new WardService();
    private static final RoomService roomService = new RoomService();
    private static final BedService bedService = new BedService();
    private static final AdmissionServices admissionServices = new AdmissionServices();
    private static final AdmissionView admissionView = new AdmissionView();
    private static final WardView wardView = new WardView();
    private static final RoomView roomView = new RoomView();
    private static final BedView bedView = new BedView();
    private static final DiagnosisView diagnosisView = new DiagnosisView();
    private static final DiagnosisService diagnosisService = new DiagnosisService();
    private static final TreatmentService treatmentService = new TreatmentService();
    private static final TreatmentView treatmentView = new TreatmentView();
    private static final MedicationService medicationService = new MedicationService();
    private static final PrescriptionService prescriptionService = new PrescriptionService();
    private static final MedicationView medicationView = new MedicationView();
    private static final PrescriptionView prescriptionView = new PrescriptionView();
    private static final BillingService billingService = new BillingService();
    private static final BillingView billingView = new BillingView();
    private static final DepartmentService departmentService = new DepartmentService();
    
    private static final Scanner scanner = new Scanner(System.in);

    // =========================================================
    // SERVICES
    // =========================================================
    private static final LaboratoryTestView labTestView = new LaboratoryTestView();
    private static final PatientService patientService = new PatientService();
    private static final DoctorService doctorService = new DoctorService();
    private static final NurseService nurseService = new NurseService();
    private static final LaboratoryTestService laboratoryTestService = new LaboratoryTestService();
    private static final UserService userService = new UserService();
    private static final MedicalRecordService medicalRecordService = new MedicalRecordService();
    private static final MedicalRecordView medicalRecordView = new MedicalRecordView();
    
    // =========================================================
    // VIEWS
    // =========================================================
    private static final PatientView patientView = new PatientView();
    private static final DoctorView doctorView = new DoctorView();
    private static final NurseView nurseView = new NurseView();

    // =========================================================
    // STATE
    // =========================================================
    private static User currentUser = null;

    public static void main(String[] args) {

        System.out.println("==============================================================");
        System.out.println("                 ABC HOSPITAL MANAGEMENT SYSTEM               ");
        System.out.println("==============================================================");

        while (true) {
            if (currentUser == null) {
                boolean loggedIn = login();
                if (!loggedIn) {
                    System.out.println("Exiting Application. Goodbye!");
                    break;
                }
            }

            displayMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    dashboardMenu();
                    break;
                case 2:
                    patientMenu();
                    break;
                case 3:
                    staffMenu();
                    break;
                case 4:
                    appointmentMenu();
                    break;
                case 5:
                    admissionAndBedMenu();
                    break;
                case 6:
                    clinicalMenu();
                    break;
                case 7:
                    laboratoryTestMenu();
                    break;
                case 8:
                    pharmacyMenu();
                    break;
                case 9:
                    billingMenu();
                    break;
                case 10:
                    hospitalAdminMenu();
                    break;
                case 11:
                    userAccountMenu();
                    break;
                case 0:
                    System.out.println("\nThank you for using ABC Hospital Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // =========================================================
    // MAIN MENU DISPLAY
    // =========================================================
    private static void displayMainMenu() {
        System.out.println();
        System.out.println("1.  Dashboard");
        System.out.println("2.  Patient Management");
        System.out.println("3.  Staff Management");
        System.out.println("4.  Appointment Management");
        System.out.println("5.  Admission & Bed Management");
        System.out.println("6.  Clinical Management");
        System.out.println("7.  Laboratory Services");
        System.out.println("8.  Pharmacy Services");
        System.out.println("9.  Billing & Payment");
        System.out.println("10. Hospital Administration");
        System.out.println("11. User Account");
        System.out.println("0.  Exit");
        System.out.println();
    }

    // =========================================================
    // LOGIN & LOGOUT
    // =========================================================
    private static boolean login() {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.println("==============================================================");
            System.out.println("                           USER LOGIN                         ");
            System.out.println("==============================================================");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            User user = userService.authenticate(username, password);

            if (user != null) {
                currentUser = user;
                System.out.println("\nLogin successful! Welcome, " + currentUser.getRole() + " " + currentUser.getUsername() + ".");
                return true;
            } else {
                attempts++;
                System.out.println("\nInvalid username or password. Remaining attempts: " + (maxAttempts - attempts));
            }
        }
        return false;
    }

    private static void logout() {
        System.out.println("\nUser " + (currentUser != null ? currentUser.getUsername() : "") + " logged out successfully.");
        currentUser = null;
    }

    // =========================================================
    // MENU HANDLERS
    // =========================================================
    private static void dashboardMenu() {
        System.out.println("\n--- Dashboard ---");
        System.out.println("Displaying system summary metrics...");
        // Add dashboard metrics or summary calculations here
    }

    // =========================================================
    // PATIENT MANAGEMENT MENU
    // =========================================================
    private static void patientMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                     PATIENT MANAGEMENT                       ");
            System.out.println("==============================================================");
            System.out.println("1.  Register Patient");
            System.out.println("2.  View All Patients");
            System.out.println("3.  Find Patient");
            System.out.println("4.  Update Patient");
            System.out.println("5.  Delete Patient");
            System.out.println("6.  View Patient Profile");
            System.out.println("7.  View Medical History");
            System.out.println("8.  View Medical Appointments");
            System.out.println("9.  View Patient Prescriptions");
            System.out.println("10. View Patient Billing History");
            System.out.println("0.  Back to Main Menu");
            System.out.println("==============================================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    findPatient();
                    break;
                case 4:
                    updatePatient();
                    break;
                case 5:
                    deletePatient();
                    break;
                case 6:
                    viewPatientProfile();
                    break;
                case 7:
                    viewMedicalHistory();
                    break;
                case 8:
                    viewMedicalAppointments();
                    break;
                case 9:
                    viewPatientPrescriptions();
                    break;
                case 10:
                    viewPatientBillingHistory();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void staffMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                      STAFF MANAGEMENT                        ");
            System.out.println("==============================================================");
            System.out.println("1. Doctor Management");
            System.out.println("2. Nurse Management");
            System.out.println("3. Pharmacist Management");
            System.out.println("4. Laboratory Technician Management");
            System.out.println("5. View All Staff");
            System.out.println("6. Find Staff");
            System.out.println("7. View Staff by Department");
            System.out.println("8. Staff Account Management");
            System.out.println("0. Back to Main Menu");
            System.out.println("==============================================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    doctorMenu();
                    break;
                case 2:
                    nurseMenu();
                    break;
                case 3:
                    pharmacistMenu();
                    break;
                case 4:
                    laboratoryTechnicianMenu();
                    break;
                case 5:
                    viewAllStaff();
                    break;
                case 6:
                    findStaff();
                    break;
                case 7:
                    viewStaffByDepartment();
                    break;
                case 8:
                    staffAccountManagement();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // =========================================================
    // APPOINTMENT MANAGEMENT MENU
    // =========================================================
    private static void appointmentMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                   APPOINTMENT MANAGEMENT                     ");
            System.out.println("==============================================================");
            System.out.println("1.  Create Appointment");
            System.out.println("2.  View All Appointments");
            System.out.println("3.  Find Appointments");
            System.out.println("4.  Update Appointments");
            System.out.println("5.  Cancel Appointments");
            System.out.println("6.  Delete Appointments");
            System.out.println("7.  View Patient Appointments");
            System.out.println("8.  View Doctor Appointments");
            System.out.println("9.  Today's Appointments");
            System.out.println("10. Upcoming Appointments");
            System.out.println("0.  Back to Main Menu");
            System.out.println("==============================================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    createAppointmentFlow();
                    break;
                case 2:
                    viewAllAppointmentsFlow();
                    break;
                case 3:
                    findAppointmentsFlow();
                    break;
                case 4:
                    updateAppointmentsFlow();
                    break;
                case 5:
                    cancelAppointmentsFlow();
                    break;
                case 6:
                    deleteAppointmentsFlow();
                    break;
                case 7:
                    viewPatientAppointmentsFlow();
                    break;
                case 8:
                    viewDoctorAppointmentsFlow();
                    break;
                case 9:
                    todaysAppointmentsFlow();
                    break;
                case 10:
                    upcomingAppointmentsFlow();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void userAccountMenu() {
        while (true) {
            System.out.println("\n--- User Account ---");
            System.out.println("1. View Current User Info");
            System.out.println("2. Logout");
            System.out.println("0. Back to Main Menu");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    if (currentUser != null) {
                        System.out.println("Username: " + currentUser.getUsername());
                        System.out.println("Role: " + currentUser.getRole());
                    }
                    break;
                case 2:
                    logout();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    // =========================================================
    // PATIENT MANAGEMENT ACTION METHODS
    // =========================================================

    private static void registerPatient() {
        System.out.println("\n--- Register Patient ---");
        System.out.println("Patient registration flow...");
        // Connect to your patient registration logic here
    }

    private static void viewAllPatients() {
        System.out.println("\n--- All Patients ---");
        List<Patient> patients = patientService.getAllPatients();
        if (patients == null || patients.isEmpty()) {
            System.out.println("No patients found in the system.");
        } else {
            patientView.displayPatients(patients);
        }
    }

    private static void findPatient() {
        System.out.println("\n--- Find Patient ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        patientView.displayPatient(patient);
    }

    private static void updatePatient() {
        System.out.println("\n--- Update Patient ---");
        int patientId = readInt("Enter Patient ID to update: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Connecting to patient update logic...");
    }

    private static void deletePatient() {
        System.out.println("\n--- Delete Patient ---");
        int patientId = readInt("Enter Patient ID to delete: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patientView.displayPatient(patient);
        System.out.print("Are you sure you want to delete this patient? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {
            boolean success = patientService.deletePatient(patientId);
            if (success) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("Unable to delete patient.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void viewPatientProfile() {
        System.out.println("\n--- View Patient Profile ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        System.out.println("==============================================================");
        System.out.println("                       PATIENT PROFILE                        ");
        System.out.println("==============================================================");
        patientView.displayPatient(patient);
        // Connect to extra profile details if available in your model
    }
    
    public static void viewMedicalHistory() {
    // Delegates directly to the full medical record viewer in MedicalRecordView
    medicalRecordView.displayFullPatientRecord();
}


    private static void viewMedicalAppointments() {
        System.out.println("\n--- View Medical Appointments ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Fetching appointments for Patient ID: " + patientId + "...");
        // Call your AppointmentService here
    }

    private static void viewPatientPrescriptions() {
        System.out.println("\n--- View Patient Prescriptions ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Fetching prescriptions for Patient ID: " + patientId + "...");
        // Call your PharmacyService/PrescriptionService here
    }

    private static void viewPatientBillingHistory() {
        System.out.println("\n--- View Patient Billing History ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Fetching billing history for Patient ID: " + patientId + "...");
        // Call your BillingService/PaymentService here
    }

    // =========================================================
    // 1. DOCTOR MANAGEMENT MENU
    // =========================================================
    private static void doctorMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                      DOCTOR MANAGEMENT                       ");
            System.out.println("==============================================================");
            System.out.println("1. Register Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Find Doctor");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. View All Doctor Appointments");
            System.out.println("0. Back");
            System.out.println("==============================================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Register Doctor selected.");
                    break;
                case 2:
                    doctorView.displayDoctors(doctorService.getAllDoctors());
                    break;
                case 3:
                    int findId = readInt("Enter Doctor ID: ");
                    System.out.println("Finding doctor with ID: " + findId);
                    break;
                case 4:
                    int updateId = readInt("Enter Doctor ID to update: ");
                    System.out.println("Updating doctor with ID: " + updateId);
                    break;
                case 5:
                    int deleteId = readInt("Enter Doctor ID to delete: ");
                    System.out.println("Deleting doctor with ID: " + deleteId);
                    break;
                case 6:
                    System.out.println("Displaying all doctor appointments...");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // =========================================================
    // 2. NURSE MANAGEMENT MENU
    // =========================================================
    private static void nurseMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                      NURSE MANAGEMENT                        ");
            System.out.println("==============================================================");
            System.out.println("1. Register Nurse");
            System.out.println("2. View All Nurses");
            System.out.println("3. Find Nurse");
            System.out.println("4. Update Nurse");
            System.out.println("5. Delete Nurse");
            System.out.println("6. View All Nurse Appointments");
            System.out.println("0. Back");
            System.out.println("==============================================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    System.out.println("Register Nurse selected.");
                    break;
                case 2:
                    nurseView.displayNurses(nurseService.getAllNurses());
                    break;
                case 3:
                    int findId = readInt("Enter Nurse ID: ");
                    System.out.println("Finding nurse with ID: " + findId);
                    break;
                case 4:
                    int updateId = readInt("Enter Nurse ID to update: ");
                    System.out.println("Updating nurse with ID: " + updateId);
                    break;
                case 5:
                    int deleteId = readInt("Enter Nurse ID to delete: ");
                    System.out.println("Deleting nurse with ID: " + deleteId);
                    break;
                case 6:
                    System.out.println("Displaying all nurse appointments...");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // =========================================================
    // 3. PHARMACIST MANAGEMENT MENU
    // =========================================================
    private static void pharmacistMenu() {
    boolean back = false;
    while (!back) {
        System.out.println("\n==============================================");
        System.out.println("            PHARMACIST MANAGEMENT             ");
        System.out.println("==============================================");
        System.out.println("1. Add New Pharmacist");
        System.out.println("2. View All Pharmacists");
        System.out.println("3. Find Pharmacist by Staff ID");
        System.out.println("4. Update Pharmacist");
        System.out.println("5. Delete Pharmacist");
        System.out.println("0. Back to Staff Menu");
        System.out.println("==============================================");

        int choice = readInt("Enter your choice: ");
        switch (choice) {
            case 1:
                addPharmacistFlow();
                break;
            case 2:
                viewAllPharmacistsFlow();
                break;
            case 3:
                findPharmacistFlow();
                break;
            case 4:
                updatePharmacistFlow();
                break;
            case 5:
                deletePharmacistFlow();
                break;
            case 0:
                back = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

    // =========================================================
    // 4. LABORATORY TECHNICIAN MANAGEMENT MENU
    // =========================================================
    private static void laboratoryTechnicianMenu() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("              LABORATORY TECHNICIAN MANAGEMENT                ");
            System.out.println("==============================================================");
            System.out.println("1. Add New Technician");
            System.out.println("2. View All Technicians");
            System.out.println("3. Find Technician by Staff ID");
            System.out.println("4. Update Technician");
            System.out.println("5. Delete Technician");
            System.out.println("0. Back to Staff Menu");
            System.out.println("==============================================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: addLabTechFlow(); break;
                case 2: viewAllLabTechsFlow(); break;
                case 3: findLabTechFlow(); break;
                case 4: updateLabTechFlow(); break;
                case 5: deleteLabTechFlow(); break;
                case 0: return;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addLabTechFlow() {
        System.out.println("\n--- Add New Laboratory Technician ---");
        System.out.print("First Name: "); String fName = scanner.nextLine().trim();
        System.out.print("Last Name: "); String lName = scanner.nextLine().trim();
        System.out.print("Gender (M/F): "); char gender = scanner.nextLine().trim().toUpperCase().charAt(0);
        System.out.print("DOB (YYYY-MM-DD): "); java.time.LocalDate dob = java.time.LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Phone: "); String phone = scanner.nextLine().trim();
        System.out.print("Email: "); String email = scanner.nextLine().trim();
        System.out.print("Qualification: "); String qual = scanner.nextLine().trim();
        System.out.print("License Number: "); String lic = scanner.nextLine().trim();
        double salary = readDouble("Salary: ");

        hospital.models.LaboratoryTechnician tech = new hospital.models.LaboratoryTechnician();
        tech.setFirstName(fName);
        tech.setLastName(lName);
        tech.setGender(gender);
        tech.setDateOfBirth(dob);
        tech.setPhone(phone);
        tech.setEmail(email);
        tech.setEmploymentDate(java.time.LocalDate.now());
        tech.setSalary(salary);
        tech.setQualification(qual);
        tech.setLicenseNumber(lic);

        if (labTechService.addLaboratoryTechnician(tech)) {
            labTechView.displayTechCreated();
        } else {
            System.out.println("Failed to add technician.");
        }
    }

    private static void viewAllLabTechsFlow() {
        labTechView.displayTechnicians(labTechService.getAllLaboratoryTechnicians());
    }

    private static void findLabTechFlow() {
        int id = readInt("Enter Technician Staff ID: ");
        labTechView.displayTechnician(labTechService.getLaboratoryTechnicianById(id));
    }

    private static void updateLabTechFlow() {
        int id = readInt("Enter Technician Staff ID to Update: ");
        hospital.models.LaboratoryTechnician tech = labTechService.getLaboratoryTechnicianById(id);
        if (tech == null) {
            labTechView.displayTechnician(null);
            return;
        }

        System.out.print("Enter New Phone [" + tech.getPhone() + "]: ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) tech.setPhone(phone);

        System.out.print("Enter New Qualification [" + tech.getQualification() + "]: ");
        String qual = scanner.nextLine().trim();
        if (!qual.isEmpty()) tech.setQualification(qual);

        System.out.print("Enter New License Number [" + tech.getLicenseNumber() + "]: ");
        String lic = scanner.nextLine().trim();
        if (!lic.isEmpty()) tech.setLicenseNumber(lic);

        if (labTechService.updateLaboratoryTechnician(tech)) {
            labTechView.displayTechUpdated();
        } else {
            System.out.println("Failed to update technician.");
        }
    }

    private static void deleteLabTechFlow() {
        int id = readInt("Enter Technician Staff ID to Delete: ");
        hospital.models.LaboratoryTechnician tech = labTechService.getLaboratoryTechnicianById(id);
        if (tech == null) {
            labTechView.displayTechnician(null);
            return;
        }

        labTechView.displayTechnician(tech);
        System.out.print("Are you sure you want to delete this technician? (Y/N): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            if (labTechService.deleteLaboratoryTechnician(id)) {
                labTechView.displayTechDeleted();
            } else {
                System.out.println("Failed to delete technician.");
            }
        }
    }
    
    // --- 5. ADMISSION & BED MANAGEMENT MENU ---
    private static void admissionAndBedMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n==============================================================");
            System.out.println("             ADMISSION & BED MANAGEMENT                       ");
            System.out.println("==============================================================");
            System.out.println("1.  Admit Patient");
            System.out.println("2.  View All Admissions");
            System.out.println("3.  Find Admissions");
            System.out.println("4.  Update Admissions");
            System.out.println("5.  Discharge Patient");
            System.out.println("6.  View Active Admissions");
            System.out.println("7.  Ward Management");
            System.out.println("8.  Room Management");
            System.out.println("9.  Bed Management");
            System.out.println("10. View Available Beds");
            System.out.println("11. View Occupied Beds");
            System.out.println("12. Nurse Assignment");
            System.out.println("0.  Back to Main Menu");
            System.out.println("==============================================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: admitPatient(); break;
                case 2: viewAllAdmissions(); break;
                case 3: findAdmissions(); break;
                case 4: updateAdmissions(); break;
                case 5: dischargePatient(); break;
                case 6: viewActiveAdmissions(); break;
                case 7: wardManagementMenu(); break;
                case 8: roomManagementMenu(); break;
                case 9: bedManagementMenu(); break;
                case 10: viewAvailableBeds(); break;
                case 11: viewOccupiedBeds(); break;
                case 12: nurseAssignmentMenu(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }
    
    // ==========================================
    // CLINICAL MANAGEMENT MENU
    // ==========================================

    public static void clinicalMenu() {
        int choice = -1;
        do {
            System.out.println("==============================================================");
            System.out.println("                    CLINICAL MANAGEMENT                       ");
            System.out.println("==============================================================");
            System.out.println("1.  Diagnosis Management");
            System.out.println("2.  Treatment Management");
            System.out.println("3.  Medical Records");
            System.out.println("4.  Nurse Assignment");
            System.out.println("5.  Patient Medical History");
            System.out.println("0.  Back to Main Menu");
            System.out.println("==============================================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    diagnosisManagementMenu();
                    break;
                case 2:
                    treatmentManagementMenu();
                    break;
                case 3:
                    medicalRecordsMenu();
                    break;
                case 4:
                    nurseAssignmentMenu();
                    break;
                case 5:
                    viewMedicalHistory();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
    
    public static void diagnosisManagementMenu() {
        int choice = -1;
        do {
            System.out.println("\n--- Diagnosis Management ---");
            System.out.println("1. Add Diagnosis");
            System.out.println("2. View Diagnoses by Patient ID");
            System.out.println("3. Update Diagnosis");
            System.out.println("4. View All Diagnoses");
            System.out.println("5. Delete Diagnosis");
            System.out.println("0. Back");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    // Delegates to the full console prompt flow in DiagnosisView
                    diagnosisView.createDiagnosis();
                    break;
                case 2:
                    int patientId = readInt("Enter Patient ID: ");
                    System.out.println("Fetching diagnoses for Patient ID: " + patientId);
                    
                    // Filters all diagnoses matching the specified patient ID
                    List<Diagnosis> allDiagnoses = diagnosisService.getAllDiagnoses();
                    boolean found = false;

                    for (Diagnosis d : allDiagnoses) {
                        if (d.getPatient() != null && d.getPatient().getPatientId() == patientId) {
                            diagnosisView.displayDiagnosis(d);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No diagnoses found for Patient ID: " + patientId);
                    }
                    break;
                case 3:
                    // Delegates to the update flow in DiagnosisView
                    diagnosisView.updateDiagnosis();
                    break;
                case 4:
                    // View all records
                    diagnosisView.viewAllDiagnoses();
                    break;
                case 5:
                    // Delete a diagnosis by ID
                    diagnosisView.deleteDiagnosis();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
    
    

    public static void treatmentManagementMenu() {
        int choice = -1;
        do {
            System.out.println("\n--- Treatment Management ---");
            System.out.println("1. Create Treatment Plan");
            System.out.println("2. View Patient Treatments");
            System.out.println("3. Update Treatment Plan Status");
            System.out.println("0. Back");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    treatmentView.createTreatmentPlan();
                    break;
                case 2:
                    treatmentView.viewPatientTreatments();
                    break;
                case 3:
                    treatmentView.updateTreatmentStatus();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

   public static void medicalRecordsMenu() {
    int choice = -1;
    do {
        System.out.println("\n--- Medical Records ---");
        System.out.println("1. Create New Patient Medical Record");
        System.out.println("2. View Full Patient Record");
        System.out.println("3. View All Registered Charts");
        System.out.println("0. Back");

        choice = readInt("Enter choice: ");

        switch (choice) {
            case 1:
                medicalRecordView.createMedicalRecordFlow();
                break;
            case 2:
                medicalRecordView.displayFullPatientRecord();
                break;
            case 3:
                medicalRecordView.displayAllMedicalRecords();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    } while (choice != 0);
}
   public static void laboratoryTestMenu() {
    int choice = -1;
    do {
        System.out.println("\n==============================================================");
        System.out.println("                    LABORATORY SERVICES                       ");
        System.out.println("==============================================================");
        System.out.println("1. Create Laboratory Test");
        System.out.println("2. View All Laboratory Tests");
        System.out.println("3. Find Laboratory Test");
        System.out.println("4. Update Laboratory Test");
        System.out.println("5. Delete Laboratory Test");
        System.out.println("6. View Patient Tests");
        System.out.println("7. View Pending Tests");
        System.out.println("8. View Completed Tests");
        System.out.println("9. Record Test Result");
        System.out.println("0. Back to Main Menu");
        System.out.println("==============================================================");

        choice = readInt("Enter choice: ");

        switch (choice) {
            case 1:
                labTestView.createLaboratoryTestFlow();
                break;
            case 2:
                labTestView.viewAllLaboratoryTestsFlow();
                break;
            case 3:
                labTestView.findLaboratoryTestFlow();
                break;
            case 4:
                labTestView.updateLaboratoryTestFlow();
                break;
            case 5:
                labTestView.deleteLaboratoryTestFlow();
                break;
            case 6:
                labTestView.viewPatientTestsFlow();
                break;
            case 7:
                labTestView.viewPendingTestsFlow();
                break;
            case 8:
                labTestView.viewCompletedTestsFlow();
                break;
            case 9:
                labTestView.recordTestResultFlow();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
        }
    } while (choice != 0);
}
    private static void pharmacyMenu() {
        System.out.println("\n==============================================================");
        System.out.println("                     PHARMACY SERVICES                        ");
        System.out.println("==============================================================");
        System.out.println("1. Medication Management");
        System.out.println("2. Prescription Management");
        System.out.println("3. Dispense Management");
        System.out.println("4. View Dispensing Records");
        System.out.println("5. View Patient Prescriptions");
        System.out.println("6. View Available Medications");
        System.out.println("7. View Low Stock Medication");
        System.out.println("8. Update Medication Stock");
        System.out.println("0. Back to Main Menu");

        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1 -> medicationView.displayMenu();
            case 2 -> prescriptionView.displayMenu();
            case 3 -> dispenseMedicationFlow();
            case 4 -> viewDispensingRecordsFlow();
            case 5 -> viewPatientPrescriptionsFlow();
            case 6 -> viewAvailableMedicationsFlow();
            case 7 -> viewLowStockMedicationsFlow();
            case 8 -> updateMedicationStockFlow();
            case 0 -> {}
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void dispenseMedicationFlow() {
        System.out.println("\n--- Dispense Management ---");
        int id = readInt("Enter Prescription ID to Dispense: ");
        Prescription prescription = prescriptionService.getPrescription(id);
        
        if (prescription == null) {
            System.out.println("Prescription not found with ID: " + id);
            return;
        }

        int medicationId = readInt("Enter Medication ID to Dispense: ");
        Medication medication = medicationService.getMedication(id);

        if (medication == null) {
            System.out.println("Medication not found with ID: " + medicationId);
            return;
        }

        int quantity = readInt("Enter Quantity to Dispense: ");
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        if (medication.getQuantityInStock() < quantity) {
            System.out.println("Insufficient stock! Available stock: " + medication.getQuantityInStock());
            return;
        }

        medication.setQuantityInStock(medication.getQuantityInStock() - quantity);
        boolean updated = medicationService.updateMedication(medication);

        if (updated) {
            System.out.println("Medication dispensed successfully!");
            System.out.println("Remaining Stock for " + medication.getName() + ": " + medication.getQuantityInStock());
        } else {
            System.out.println("Failed to update medication stock during dispensing.");
        }
    }

    private static void viewDispensingRecordsFlow() {
        System.out.println("\n--- View Dispensing Records ---");
        System.out.println("Fetching all fulfilled and dispensed prescription activity records...");
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        if (prescriptions.isEmpty()) {
            System.out.println("No dispensing records available.");
        } else {
            for (Prescription p : prescriptions) {
                System.out.printf("Dispensed Record -> Prescription ID: %-5d | Patient ID: %-5d | Doctor ID: %-5d | Date: %s%n",
                        p.getId(), p.getPatient().getPatientId(), p.getDoctor().getId(), p.getPrescriptionDate());
            }
        }
    }

    private static void viewPatientPrescriptionsFlow() {
        System.out.println("\n--- View Patient Prescriptions ---");
        int patientId = readInt("Enter Patient ID: ");
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        boolean found = false;

        System.out.printf("%-15s %-12s %-12s %-15s%n", "Prescription ID", "Patient ID", "Doctor ID", "Date");
        System.out.println("--------------------------------------------------");
        for (Prescription p : prescriptions) {
            if (p.getPatient() != null && p.getPatient().getPatientId() == patientId) {
                System.out.printf("%-15d %-12d %-12d %-15s%n",
                        p.getId(), p.getPatient().getPatientId(), p.getDoctor().getId(), p.getPrescriptionDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No prescriptions found for Patient ID: " + patientId);
        }
    }

    private static void viewAvailableMedicationsFlow() {
        System.out.println("\n--- Available Medications ---");
        List<Medication> list = medicationService.getAllMedications();
        boolean found = false;

        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Name", "Form", "Price", "Stock");
        System.out.println("---------------------------------------------------------------");
        for (Medication m : list) {
            if (m.getQuantityInStock() > 0) {
                System.out.printf("%-5d %-20s %-15s $%-9.2f %-10d%n",
                        m.getId(), m.getName(), m.getDosageForm(), m.getPrice(), m.getQuantityInStock());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available medications in stock.");
        }
    }

    private static void viewLowStockMedicationsFlow() {
        System.out.println("\n--- Low Stock Medications ---");
        int threshold = readInt("Enter Low Stock Threshold Quantity (e.g., 10): ");
        List<Medication> list = medicationService.getAllMedications();
        boolean found = false;

        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Name", "Form", "Price", "Stock");
        System.out.println("---------------------------------------------------------------");
        for (Medication m : list) {
            if (m.getQuantityInStock() <= threshold) {
                System.out.printf("%-5d %-20s %-15s $%-9.2f %-10d%n",
                        m.getId(), m.getName(), m.getDosageForm(), m.getPrice(), m.getQuantityInStock());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No medications are below the stock threshold of " + threshold + ".");
        }
    }

    private static void updateMedicationStockFlow() {
        System.out.println("\n--- Update Medication Stock ---");
        int medId = readInt("Enter Medication ID to update stock: ");
        Medication medication = medicationService.getMedication(medId);

        if (medication == null) {
            System.out.println("Medication not found with ID: " + medId);
            return;
        }

        System.out.println("Current stock for " + medication.getName() + ": " + medication.getQuantityInStock());
        int newStock = readInt("Enter New Stock Quantity: ");
        if (newStock < 0) {
            System.out.println("Stock quantity cannot be negative.");
            return;
        }

        medication.setQuantityInStock(newStock);
        if (medicationService.updateMedication(medication)) {
            System.out.println("Medication stock updated successfully!");
        } else {
            System.out.println("Failed to update medication stock.");
        }
    }

    public static void billingMenu() {
    int choice;
    do {
        System.out.println("\n==============================================================");
        System.out.println("                     BILLING & PAYMENT                        ");
        System.out.println("==============================================================");
        System.out.println("1.  Create Invoice");
        System.out.println("2.  View All Invoices");
        System.out.println("3.  Find Invoice");
        System.out.println("4.  Update Invoice");
        System.out.println("5.  Add Invoice Item");
        System.out.println("6.  View Invoice Items");
        System.out.println("7.  Record Payment");
        System.out.println("8.  View Payments");
        System.out.println("9.  Patient Billing History");
        System.out.println("10. Outstanding Bills");
        System.out.println("0.  Back to Main Menu");
        System.out.println("==============================================================");

        choice = readInt("Enter choice: ");

        switch (choice) {
            case 1 -> createInvoiceFlow();
            case 2 -> viewAllInvoicesFlow();
            case 3 -> findInvoiceFlow();
            case 4 -> updateInvoiceFlow();
            case 5 -> addInvoiceItemFlow();
            case 6 -> viewInvoiceItemsFlow();
            case 7 -> recordPaymentFlow();
            case 8 -> viewPaymentsFlow();
            case 9 -> patientBillingHistoryFlow();
            case 10 -> outstandingBillsFlow();
            case 0 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (choice != 0);
}
    public static void createInvoiceFlow() {
    System.out.println("\n--- Create Invoice ---");
    int patientId = readInt("Enter Patient ID: ");
    Patient patient = patientService.getPatientById(patientId);
    if (patient == null) {
        System.out.println("Patient not found.");
        return;
    }

    Invoice invoice = new Invoice();
    invoice.setPatient(patient);
    invoice.setInvoiceDate(LocalDate.now());
    invoice.setStatus("Unpaid");
    invoice.setTotalAmount(0.0);

}

public static void viewAllInvoicesFlow() {
    System.out.println("\n--- All Invoices ---");
    List<Invoice> invoices = billingService.getAllInvoices();
    if (invoices.isEmpty()) {
        System.out.println("No invoices found.");
    } else {
        billingView.displayInvoices(invoices);
    }
}

public static void findInvoiceFlow() {
    System.out.println("\n--- Find Invoice ---");
    int invoiceId = readInt("Enter Invoice ID: ");
    Invoice invoice = billingService.getInvoiceById(invoiceId);
    if (invoice == null) {
        System.out.println("Invoice not found.");
    } else {
        billingView.displayInvoice(invoice);
    }
}

public static void updateInvoiceFlow() {
    System.out.println("\n--- Update Invoice Status ---");
    int invoiceId = readInt("Enter Invoice ID to update: ");
    Invoice invoice = billingService.getInvoiceById(invoiceId);
    if (invoice == null) {
        System.out.println("Invoice not found.");
        return;
    }

    System.out.println("Current Status: " + invoice.getStatus());
    System.out.print("Enter New Status (Paid/Unpaid/Partially Paid/Cancelled) [Leave blank to keep current]: ");
    String newStatus = scanner.nextLine().trim();

    if (!newStatus.isEmpty()) {
        invoice.setStatus(newStatus);
        boolean updated = billingService.updateInvoice(invoice);
        if (updated) {
            System.out.println("Invoice status updated successfully.");
        } else {
            System.out.println("Failed to update invoice.");
        }
    } else {
        System.out.println("Update cancelled.");
    }
}

public static void addInvoiceItemFlow() {
    System.out.println("\n--- Add Invoice Item ---");
    int invoiceId = readInt("Enter Invoice ID: ");
    Invoice invoice = billingService.getInvoiceById(invoiceId);
    if (invoice == null) {
        System.out.println("Invoice not found.");
        return;
    }

    System.out.print("Enter Item Description: ");
    String description = scanner.nextLine().trim();
    double amount = readDouble("Enter Amount ($): ");

    InvoiceItem item = new InvoiceItem();
    item.setId(invoiceId);
    item.setDescription(description);
    item.setAmount(amount);

    boolean added = billingService.addInvoiceItem(item);
    if (added) {
        // Recalculate total balance
        invoice.setTotalAmount(invoice.getTotalAmount() + amount);
        billingService.updateInvoice(invoice);
        System.out.println("Item added to invoice successfully.");
    } else {
        System.out.println("Failed to add invoice item.");
    }
}

public static void viewInvoiceItemsFlow() {
    System.out.println("\n--- View Invoice Line Items ---");
    int invoiceId = readInt("Enter Invoice ID: ");
    List<InvoiceItem> items = billingService.getInvoiceItemsByInvoiceId(invoiceId);
    if (items.isEmpty()) {
        System.out.println("No line items found for Invoice ID: " + invoiceId);
    } else {
        billingView.displayInvoiceItems(items);
    }
}

public static void recordPaymentFlow() {
    System.out.println("\n--- Record Payment ---");
    int invoiceId = readInt("Enter Invoice ID: ");
    Invoice invoice = billingService.getInvoiceById(invoiceId);
    if (invoice == null) {
        System.out.println("Invoice not found.");
        return;
    }

    double paymentAmount = readDouble("Enter Payment Amount ($): ");
    System.out.print("Enter Payment Method (Cash/Credit Card/Insurance/Bank Transfer): ");
    String method = scanner.nextLine().trim();

    Payment payment = new Payment();
    payment.setInvoice(invoice);
    payment.setAmount(paymentAmount);
    payment.setPaymentDate(LocalDate.now());
    payment.setPaymentMethod(method);

    boolean recorded = billingService.recordPayment(payment);
    if (recorded) {
        double totalPaid = billingService.getTotalPaidForInvoice(invoiceId);
        if (totalPaid >= invoice.getTotalAmount()) {
            invoice.setStatus("Paid");
        } else if (totalPaid > 0) {
            invoice.setStatus("Partially Paid");
        }
        billingService.updateInvoice(invoice);
        System.out.println("Payment recorded successfully!");
    } else {
        System.out.println("Failed to record payment.");
    }
}

public static void viewPaymentsFlow() {
    System.out.println("\n--- View Payments ---");
    int invoiceId = readInt("Enter Invoice ID: ");
    List<Payment> payments = billingService.getPaymentsByInvoiceId(invoiceId);
    if (payments.isEmpty()) {
        System.out.println("No payments recorded for Invoice ID: " + invoiceId);
    } else {
        billingView.displayPayments(payments);
    }
}

public static void patientBillingHistoryFlow() {
    System.out.println("\n--- Patient Billing History ---");
    int patientId = readInt("Enter Patient ID: ");
    List<Invoice> history = billingService.getInvoicesByPatientId(patientId);
    if (history.isEmpty()) {
        System.out.println("No billing history found for Patient ID: " + patientId);
    } else {
        billingView.displayInvoices(history);
    }
}

public static void outstandingBillsFlow() {
    System.out.println("\n--- Outstanding Bills ---");
    List<Invoice> outstanding = billingService.getOutstandingInvoices();
    if (outstanding.isEmpty()) {
        System.out.println("No outstanding or unpaid bills.");
    } else {
        billingView.displayInvoices(outstanding);
    }
}

    // --- 10. HOSPITAL ADMINISTRATION MENU ---
    private static void hospitalAdminMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==============================================================");
            System.out.println("                  HOSPITAL ADMINISTRATION                     ");
            System.out.println("==============================================================");
            System.out.println("1. Department Management");
            System.out.println("2. Ward Management");
            System.out.println("3. Room Management");
            System.out.println("4. Bed Management");
            System.out.println("5. User Management");
            System.out.println("6. Role Management");
            System.out.println("7. System Reports");
            System.out.println("0. Back to Main Menu");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> departmentManagementMenu();
                case 2 -> wardManagementMenu();
                case 3 -> roomManagementMenu();
                case 4 -> bedManagementMenu();
                case 5 -> userAccountMenu();
                case 6 -> roleManagementMenu();
                case 7 -> systemReportsMenu();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // --- Department Management ---
    private static void departmentManagementMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Department Management ---");
            System.out.println("1. Add Department");
            System.out.println("2. View All Departments");
            System.out.println("3. Find Department");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println("0. Back");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Department Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine().trim();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine().trim();

                    Department dept = new Department(0, name, desc, location);
                    departmentService.addDepartment(dept);
                    System.out.println("Department added successfully.");
                }
                case 2 -> {
                    List<Department> list = departmentService.getAllDepartments();
                    if (list.isEmpty()) {
                        System.out.println("No departments found.");
                    } else {
                        System.out.printf("%-10s %-25s %-30s%n", "ID", "Name", "Description");
                        System.out.println("------------------------------------------------------------------");
                        for (Department d : list) {
                            System.out.printf("%-10d %-25s %-30s%n", d.getId(), d.getName(), d.getDescription());
                        }
                    }
                }
                case 3 -> {
                    int id = readInt("Enter Department ID: ");
                    Department d = departmentService.getDepartmentById(id);
                    if (d != null) {
                        System.out.println("ID: " + d.getId() + " | Name: " + d.getName() + " | Description: " + d.getDescription());
                    } else {
                        System.out.println("Department not found.");
                    }
                }
                case 4 -> {
                    int id = readInt("Enter Department ID to Update: ");
                    Department d = departmentService.getDepartmentById(id);
                    if (d != null) {
                        System.out.print("Enter New Name [" + d.getName() + "]: ");
                        String name = scanner.nextLine().trim();
                        if (!name.isEmpty()) d.setName(name);

                        System.out.print("Enter New Description [" + d.getDescription() + "]: ");
                        String desc = scanner.nextLine().trim();
                        if (!desc.isEmpty()) d.setDescription(desc);

                        if (departmentService.updateDepartment(d)) {
                            System.out.println("Department updated successfully.");
                        } else {
                            System.out.println("Failed to update department.");
                        }
                    } else {
                        System.out.println("Department not found.");
                    }
                }
                case 5 -> {
                    int id = readInt("Enter Department ID to Delete: ");
                    if (departmentService.deleteDepartment(id)) {
                        System.out.println("Department deleted successfully.");
                    } else {
                        System.out.println("Failed to delete department.");
                    }
                }
                case 0 -> {}
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- Role Management ---
    private static void roleManagementMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Role Management ---");
            System.out.println("1. View All Defined Roles");
            System.out.println("2. Update User Role");
            System.out.println("0. Back");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAvailable System Roles:");
                    for (StaffRole role : StaffRole.values()) {
                        System.out.println(" - " + role.name());
                    }
                }
                case 2 -> updateUserRoleFlow();
                case 0 -> {}
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- System Reports ---
    private static void systemReportsMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- System Reports ---");
            System.out.println("1. Patient Census Summary");
            System.out.println("2. Occupancy & Bed Report");
            System.out.println("3. Financial & Revenue Report");
            System.out.println("4. Staff Allocation Summary");
            System.out.println("0. Back");

            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    List<Patient> pts = patientService.getAllPatients();
                    List<Admission> activeAdm = admissionServices.getAllAdmissions().stream().filter(Admission::isActive).toList();
                    System.out.println("\n[PATIENT CENSUS SUMMARY]");
                    System.out.println("Total Registered Patients: " + pts.size());
                    System.out.println("Currently Admitted Patients: " + activeAdm.size());
                }
                case 2 -> {
                    List<Bed> totalBeds = bedService.getAllBeds();
                    List<Bed> occupiedBeds = bedService.getOccupiedBeds();
                    List<Bed> availableBeds = bedService.getAvailableBeds();
                    System.out.println("\n[OCCUPANCY & BED REPORT]");
                    System.out.println("Total Beds: " + totalBeds.size());
                    System.out.println("Occupied Beds: " + occupiedBeds.size());
                    System.out.println("Available Beds: " + availableBeds.size());
                    if (!totalBeds.isEmpty()) {
                        double rate = ((double) occupiedBeds.size() / totalBeds.size()) * 100;
                        System.out.printf("Occupancy Rate: %.2f%%%n", rate);
                    }
                }
                case 3 -> {
                    List<Invoice> invoices = billingService.getAllInvoices();
                    double totalBilled = invoices.stream().mapToDouble(Invoice::getTotalAmount).sum();
                    System.out.println("\n[FINANCIAL & REVENUE REPORT]");
                    System.out.println("Total Invoices Generated: " + invoices.size());
                    System.out.printf("Total Revenue Billed: $%.2f%n", totalBilled);
                }
                case 4 -> {
                    System.out.println("\n[STAFF ALLOCATION SUMMARY]");
                    System.out.println("Total Doctors: " + doctorService.getAllDoctors().size());
                    System.out.println("Total Nurses: " + nurseService.getAllNurses().size());
                    System.out.println("Total Pharmacists: " + pharmacistService.getAllPharmacists().size());
                    System.out.println("Total Lab Technicians: " + labTechService.getAllLaboratoryTechnicians().size());
                }
                case 0 -> {}
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    

    // =========================================================
    // 8. STAFF ACCOUNT MANAGEMENT MENU
    // =========================================================
    private static void staffAccountManagement() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("                   STAFF ACCOUNT MANAGEMENT                   ");
            System.out.println("==============================================================");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Find User");
            System.out.println("4. Update User Role");
            System.out.println("5. Deactivate User");
            System.out.println("6. Activate User");
            System.out.println("7. Reset Password");
            System.out.println("0. Back");
            System.out.println("==============================================================");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    createUserFlow();
                    break;
                case 2:
                    viewAllUsersFlow();
                    break;
                case 3:
                    findUserFlow();
                    break;
                case 4:
                    updateUserRoleFlow();
                    break;
                case 5:
                    deactivateUserFlow();
                    break;
                case 6:
                    activateUserFlow();
                    break;
                case 7:
                    resetPasswordFlow();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // =========================================================
    // STAFF ACCOUNT ACTION FLOWS
    // =========================================================

    private static void createUserFlow() {
        System.out.println("\n--- Create New User ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Select Staff Role:");
        System.out.println("1. Doctor\n2. Nurse\n3. Pharmacist\n4. Lab Technician\n5. Staff");
        int roleChoice = readInt("Enter Role choice: ");

        hospital.models.StaffRole role;
        switch (roleChoice) {
            case 1: role = hospital.models.StaffRole.DOCTOR; break;
            case 2: role = hospital.models.StaffRole.NURSE; break;
            case 3: role = hospital.models.StaffRole.PHARMACIST; break;
            case 4: role = hospital.models.StaffRole.LABORATORY_TECHNICIAN; break;
            default: role = hospital.models.StaffRole.STAFF; break;
        }

        boolean success = userService.createUser(username, password, role);
        if (success) {
            System.out.println("User created successfully!");
        } else {
            System.out.println("Failed to create user. Username may already exist.");
        }
    }

    private static void viewAllUsersFlow() {
        System.out.println("\n==============================================================");
        System.out.println("                       ALL SYSTEM USERS                       ");
        System.out.println("==============================================================");
        List<User> allUsers = userService.getAllUsers();
        if (allUsers == null || allUsers.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User u : allUsers) {
                System.out.printf("Username: %-20s | Role: %-20s | Active: %b%n",
                        u.getUsername(), u.getRole(), u.isActive());
            }
        }
        System.out.println("==============================================================");
    }

    private static void findUserFlow() {
        System.out.println("\n--- Find User ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        User user = userService.findUserByUsername(username);
        if (user != null) {
            System.out.println("\n[User Record Found]");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role    : " + user.getRole());
            System.out.println("Status  : " + (user.isActive() ? "Active" : "Deactivated"));
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUserRoleFlow() {
        System.out.println("\n--- Update User Role ---");
        System.out.print("Enter Username to update: ");
        String username = scanner.nextLine().trim();

        User user = userService.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Select New Staff Role:");
        System.out.println("1. Doctor\n2. Nurse\n3. Pharmacist\n4. Lab Technician\n5. Staff");
        int roleChoice = readInt("Enter choice: ");

        hospital.models.StaffRole newRole;
        switch (roleChoice) {
            case 1: newRole = hospital.models.StaffRole.DOCTOR; break;
            case 2: newRole = hospital.models.StaffRole.NURSE; break;
            case 3: newRole = hospital.models.StaffRole.PHARMACIST; break;
            case 4: newRole = hospital.models.StaffRole.LABORATORY_TECHNICIAN; break;
            default: newRole = hospital.models.StaffRole.STAFF; break;
        }

        if (userService.updateUserRole(username, newRole)) {
            System.out.println("Role updated successfully.");
        } else {
            System.out.println("Unable to update role.");
        }
    }

    private static void deactivateUserFlow() {
        System.out.println("\n--- Deactivate User ---");
        System.out.print("Enter Username to deactivate: ");
        String username = scanner.nextLine().trim();

        if (userService.setAccountStatus(username, false)) {
            System.out.println("User account deactivated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void activateUserFlow() {
        System.out.println("\n--- Activate User ---");
        System.out.print("Enter Username to activate: ");
        String username = scanner.nextLine().trim();

        if (userService.setAccountStatus(username, true)) {
            System.out.println("User account activated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void resetPasswordFlow() {
        System.out.println("\n--- Reset User Password ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine().trim();

        if (userService.resetPassword(username, newPassword)) {
            System.out.println("Password reset successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
    
    private static void viewAllStaff() {
        System.out.println("\n==============================================================");
        System.out.println("                      ALL HOSPITAL STAFF                      ");
        System.out.println("==============================================================");

        System.out.println("\n--- DOCTORS ---");
        List<hospital.models.Doctor> doctors = doctorService.getAllDoctors();
        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            doctorView.displayDoctors(doctors);
        }

        System.out.println("\n--- NURSES ---");
        List<hospital.models.Nurse> nurses = nurseService.getAllNurses();
        if (nurses == null || nurses.isEmpty()) {
            System.out.println("No nurses found.");
        } else {
            nurseView.displayNurses(nurses);
        }

        System.out.println("==============================================================");
    }

    private static void findStaff() {
        System.out.println("\n==============================================================");
        System.out.println("                          FIND STAFF                          ");
        System.out.println("==============================================================");

        int staffId = readInt("Enter Staff ID: ");
        boolean found = false;

        // Search Doctors
        hospital.models.Doctor doctor = doctorService.getDoctorById(staffId);
        if (doctor != null) {
            System.out.println("\n[Staff Found in Doctors]");
            doctorView.displayDoctor(doctor);
            found = true;
        }

        // Search Nurses
        hospital.models.Nurse nurse = nurseService.getNurseById(staffId);
        if (nurse != null) {
            System.out.println("\n[Staff Found in Nurses]");
            nurseView.displayNurse(nurse);
            found = true;
        }

        if (!found) {
            System.out.println("No staff member found with ID: " + staffId);
        }
    }
    
    private static void viewStaffByDepartment() {
        System.out.println("\n==============================================================");
        System.out.println("                   VIEW STAFF BY DEPARTMENT                   ");
        System.out.println("==============================================================");

        System.out.print("Enter Department Name (e.g., Cardiology, ER, Pediatrics): ");
        String dept = scanner.nextLine().trim();

        if (dept.isEmpty()) {
            System.out.println("Department name cannot be empty.");
            return;
        }

        System.out.println("\n--- DOCTORS IN " + dept.toUpperCase() + " ---");
        List<hospital.models.Doctor> doctors = doctorService.getAllDoctors();
        boolean doctorFound = false;

        if (doctors != null) {
            for (hospital.models.Doctor d : doctors) {
                // Null checks to prevent NullPointerException if doctor or department is null
                if (d.getDepartment() != null && d.getDepartment().getName() != null) {
                    if (d.getDepartment().getName().equalsIgnoreCase(dept)) {
                        doctorView.displayDoctor(d);
                        doctorFound = true;
                    }
                }
            }
        }
        if (!doctorFound) {
            System.out.println("No doctors found in " + dept);
        }

        System.out.println("\n--- NURSES IN " + dept.toUpperCase() + " ---");
        List<hospital.models.Nurse> nurses = nurseService.getAllNurses();
        boolean nurseFound = false;

        if (nurses != null) {
            for (hospital.models.Nurse n : nurses) {
                // Null checks to prevent NullPointerException
                if (n.getDepartment() != null && n.getDepartment().getName() != null) {
                    if (n.getDepartment().getName().equalsIgnoreCase(dept)) {
                        nurseView.displayNurse(n);
                        nurseFound = true;
                    }
                }
            }
        }
        if (!nurseFound) {
            System.out.println("No nurses found in " + dept);
        }

        System.out.println("==============================================================");
        
    }
    
    // =========================================================
    // APPOINTMENT ACTION FLOWS
    // =========================================================

    private static void createAppointmentFlow() {
        System.out.println("\n--- Create Appointment ---");
        
        int patientId = readInt("Enter Patient ID: ");
        hospital.models.Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int doctorId = readInt("Enter Doctor ID: ");
        hospital.models.Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();

        System.out.print("Enter Time (HH:mm, e.g. 09:30 or 14:00): ");
        String timeStr = scanner.nextLine().trim();

        java.time.LocalDateTime appointmentDateTime;
        try {
            java.time.LocalDate date = java.time.LocalDate.parse(dateStr);
            java.time.LocalTime time = java.time.LocalTime.parse(timeStr);
            appointmentDateTime = java.time.LocalDateTime.of(date, time);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use YYYY-MM-DD for date and HH:mm for time.");
            return;
        }

        System.out.print("Enter Reason / Notes: ");
        String reason = scanner.nextLine().trim();

        hospital.models.Appointment appointment = new hospital.models.Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDateTime);
        appointment.setReason(reason);
        appointment.setStatus("Scheduled");

        boolean success = appointmentService.addAppointment(appointment);
        if (success) {
            appointmentView.displayAppointmentCreated();
        } else {
            System.out.println("Failed to create appointment.");
        }
    }

    private static void viewAllAppointmentsFlow() {
        System.out.println("\n==============================================================");
        System.out.println("                     ALL APPOINTMENTS                         ");
        System.out.println("==============================================================");
        List<hospital.models.Appointment> appointments = appointmentService.getAllAppointments();
        appointmentView.displayAppointments(appointments);
    }

    private static void findAppointmentsFlow() {
        System.out.println("\n--- Find Appointment ---");
        int appointmentId = readInt("Enter Appointment ID: ");
        hospital.models.Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        appointmentView.displayAppointment(appointment);
    }

    private static void updateAppointmentsFlow() {
        System.out.println("\n--- Update Appointment ---");
        int appointmentId = readInt("Enter Appointment ID to update: ");
        hospital.models.Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            appointmentView.displayAppointment(null);
            return;
        }

        System.out.println("\nLeave input blank to keep existing values.");

        System.out.print("Enter New Date (YYYY-MM-DD) [" + appointment.getAppointmentDate().toLocalDate() + "]: ");
        String dateStr = scanner.nextLine().trim();

        System.out.print("Enter New Time (HH:mm) [" + appointment.getAppointmentDate().toLocalTime() + "]: ");
        String timeStr = scanner.nextLine().trim();

        if (!dateStr.isEmpty() || !timeStr.isEmpty()) {
            try {
                java.time.LocalDate date = dateStr.isEmpty() 
                        ? appointment.getAppointmentDate().toLocalDate() 
                        : java.time.LocalDate.parse(dateStr);
                java.time.LocalTime time = timeStr.isEmpty() 
                        ? appointment.getAppointmentDate().toLocalTime() 
                        : java.time.LocalTime.parse(timeStr);
                appointment.setAppointmentDate(java.time.LocalDateTime.of(date, time));
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date/time format. Update aborted.");
                return;
            }
        }

        System.out.print("Enter New Reason [" + appointment.getReason() + "]: ");
        String reason = scanner.nextLine().trim();
        if (!reason.isEmpty()) {
            appointment.setReason(reason);
        }

        System.out.print("Enter New Status [" + appointment.getStatus() + "]: ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) {
            appointment.setStatus(status);
        }

        boolean success = appointmentService.updateAppointment(appointment);
        if (success) {
            appointmentView.displayAppointmentUpdated();
        } else {
            System.out.println("Failed to update appointment.");
        }
    }

    private static void cancelAppointmentsFlow() {
        System.out.println("\n--- Cancel Appointment ---");
        int appointmentId = readInt("Enter Appointment ID to cancel: ");
        hospital.models.Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            appointmentView.displayAppointment(null);
            return;
        }

        System.out.print("Enter Cancellation Reason: ");
        String cancelReason = scanner.nextLine().trim();

        appointment.setStatus("Cancelled");
        if (!cancelReason.isEmpty()) {
            appointment.setNotes(cancelReason);
        }

        boolean success = appointmentService.updateAppointment(appointment);
        if (success) {
            System.out.println("Appointment status set to Cancelled.");
        } else {
            System.out.println("Failed to cancel appointment.");
        }
    }

    private static void deleteAppointmentsFlow() {
        System.out.println("\n--- Delete Appointment ---");
        int appointmentId = readInt("Enter Appointment ID to delete: ");
        hospital.models.Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            appointmentView.displayAppointment(null);
            return;
        }

        appointmentView.displayAppointment(appointment);
        System.out.print("Are you sure you want to permanently delete this appointment? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {
            boolean success = appointmentService.deleteAppointment(appointmentId);
            if (success) {
                appointmentView.displayAppointmentDeleted();
            } else {
                System.out.println("Failed to delete appointment.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void viewPatientAppointmentsFlow() {
        System.out.println("\n--- View Patient Appointments ---");
        int patientId = readInt("Enter Patient ID: ");
        List<hospital.models.Appointment> appointments = appointmentService.getAppointmentsByPatient(patientId);
        appointmentView.displayAppointments(appointments);
    }

    private static void viewDoctorAppointmentsFlow() {
        System.out.println("\n--- View Doctor Appointments ---");
        int doctorId = readInt("Enter Doctor ID: ");
        List<hospital.models.Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        appointmentView.displayAppointments(appointments);
    }

    private static void todaysAppointmentsFlow() {
        System.out.println("\n==============================================================");
        System.out.println("                    TODAY'S APPOINTMENTS                      ");
        System.out.println("==============================================================");
        
        java.time.LocalDate today = java.time.LocalDate.now();
        List<hospital.models.Appointment> all = appointmentService.getAllAppointments();
        List<hospital.models.Appointment> todaysList = new ArrayList<>();

        if (all != null) {
            for (hospital.models.Appointment appt : all) {
                if (appt.getAppointmentDate() != null && appt.getAppointmentDate().toLocalDate().equals(today)) {
                    todaysList.add(appt);
                }
            }
        }

        appointmentView.displayAppointments(todaysList);
    }

    private static void upcomingAppointmentsFlow() {
        System.out.println("\n==============================================================");
        System.out.println("                   UPCOMING APPOINTMENTS                      ");
        System.out.println("==============================================================");
        
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        List<hospital.models.Appointment> all = appointmentService.getAllAppointments();
        List<hospital.models.Appointment> upcomingList = new ArrayList<>();

        if (all != null) {
            for (hospital.models.Appointment appt : all) {
                if (appt.getAppointmentDate() != null && appt.getAppointmentDate().isAfter(now)) {
                    upcomingList.add(appt);
                }
            }
        }

        appointmentView.displayAppointments(upcomingList);
    }
    
    private static void addPharmacistFlow() {
    System.out.println("\n--- Add New Pharmacist ---");
    
    System.out.print("First Name: ");
    String fName = scanner.nextLine().trim();
    
    System.out.print("Last Name: ");
    String lName = scanner.nextLine().trim();
    
    System.out.print("Gender (M/F): ");
    char gender = scanner.nextLine().trim().toUpperCase().charAt(0);
    
    System.out.print("DOB (YYYY-MM-DD): ");
    LocalDate dob = LocalDate.parse(scanner.nextLine().trim());
    
    System.out.print("Phone: ");
    String phone = scanner.nextLine().trim();
    
    System.out.print("Email: ");
    String email = scanner.nextLine().trim();
    
    System.out.print("Qualification: ");
    String qual = scanner.nextLine().trim();
    
    System.out.print("License Number: ");
    String lic = scanner.nextLine().trim();
    
    double salary = readDouble("Salary: ");

    Pharmacist ph = new Pharmacist();
    ph.setFirstName(fName);
    ph.setLastName(lName);
    ph.setGender(gender);
    ph.setDateOfBirth(dob);
    ph.setPhone(phone);
    ph.setEmail(email);
    ph.setEmploymentDate(LocalDate.now());
    ph.setSalary(salary);
    ph.setQualification(qual);
    ph.setLicenseNumber(lic);

    boolean success = pharmacistService.addPharmacist(ph);
    if (success) {
        pharmacistView.displayPharmacistCreated();
    } else {
        System.out.println("Failed to add pharmacist.");
    }
}

private static void viewAllPharmacistsFlow() {
    List<Pharmacist> pharmacists = pharmacistService.getAllPharmacists();
    pharmacistView.displayPharmacists(pharmacists);
}

private static void findPharmacistFlow() {
    int id = readInt("Enter Pharmacist Staff ID: ");
    Pharmacist ph = pharmacistService.getPharmacistById(id);
    if (ph != null) {
        pharmacistView.displayPharmacist(ph);
    } else {
        System.out.println("Pharmacist not found with ID: " + id);
    }
}

private static void updatePharmacistFlow() {
    int id = readInt("Enter Pharmacist Staff ID to Update: ");
    Pharmacist ph = pharmacistService.getPharmacistById(id);
    if (ph == null) {
        System.out.println("Pharmacist not found with ID: " + id);
        return;
    }

    System.out.println("Leave input blank to keep current value.");

    System.out.print("First Name [" + ph.getFirstName() + "]: ");
    String input = scanner.nextLine().trim();
    if (!input.isEmpty()) ph.setFirstName(input);

    System.out.print("Last Name [" + ph.getLastName() + "]: ");
    input = scanner.nextLine().trim();
    if (!input.isEmpty()) ph.setLastName(input);

    System.out.print("Phone [" + ph.getPhone() + "]: ");
    input = scanner.nextLine().trim();
    if (!input.isEmpty()) ph.setPhone(input);

    System.out.print("Qualification [" + ph.getQualification() + "]: ");
    input = scanner.nextLine().trim();
    if (!input.isEmpty()) ph.setQualification(input);

    System.out.print("License Number [" + ph.getLicenseNumber() + "]: ");
    input = scanner.nextLine().trim();
    if (!input.isEmpty()) ph.setLicenseNumber(input);

    boolean success = pharmacistService.updatePharmacist(ph);
    if (success) {
        pharmacistView.displayPharmacistUpdated();
    } else {
        System.out.println("Failed to update pharmacist.");
    }
}

private static void deletePharmacistFlow() {
    int id = readInt("Enter Pharmacist Staff ID to Delete: ");
    System.out.print("Are you sure you want to delete this pharmacist? (Y/N): ");
    String confirm = scanner.nextLine().trim();
    if (confirm.equalsIgnoreCase("Y")) {
        boolean success = pharmacistService.deletePharmacist(id);
        if (success) {
            pharmacistView.displayPharmacistDeleted();
        } else {
            System.out.println("Failed to delete pharmacist.");
        }
    } else {
        System.out.println("Delete cancelled.");
    }
}
    
    private static void admitPatient() {
        System.out.println("\n--- Admit Patient ---");
        int patientId = readInt("Enter Patient ID: ");
        Patient p = patientService.getPatientById(patientId);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        List<Bed> availableBeds = bedService.getAvailableBeds();
        if (availableBeds.isEmpty()) {
            System.out.println("No available beds.");
            return;
        }

        bedView.displayBeds(availableBeds);
        int bedId = readInt("Enter Bed ID: ");
        Bed bed = bedService.getBedById(bedId);
        if (bed == null || bed.isOccupied()) {
            System.out.println("Invalid or occupied bed.");
            return;
        }

        System.out.print("Reason for Admission: ");
        String reason = scanner.nextLine().trim();

        Admission adm = new Admission();
        adm.setPatient(p);
        adm.setBed(bed);
        adm.setAdmissionDate(LocalDate.now());
        adm.setReason(reason.isEmpty() ? "Not specified" : reason);
        adm.setStatus("Admitted");

        boolean ok = admissionServices.admitPatient(adm);
        if (ok) {
            bed.setOccupied(true);
            bedService.updateBed(bed);
            System.out.println("Patient admitted successfully.");
        } else {
            System.out.println("Failed to admit patient.");
        }
    }

    private static void viewAllAdmissions() {
        admissionView.displayAdmissions(admissionServices.getAllAdmissions());
    }

    private static void findAdmissions() {
        int id = readInt("Enter Admission ID: ");
        Admission adm = admissionServices.getAdmissionById(id);
        if (adm != null) {
            List<Admission> list = new ArrayList<>();
            list.add(adm);
            admissionView.displayAdmissions(list);
        } else {
            System.out.println("Admission not found.");
        }
    }

    private static void updateAdmissions() {
        int id = readInt("Enter Admission ID to Update: ");
        Admission adm = admissionServices.getAdmissionById(id);
        if (adm == null) {
            System.out.println("Admission not found.");
            return;
        }

        System.out.print("Enter New Reason [" + adm.getReason() + "]: ");
        String reason = scanner.nextLine().trim();
        if (!reason.isEmpty()) adm.setReason(reason);

        System.out.print("Enter New Status [" + adm.getStatus() + "]: ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) adm.setStatus(status);

        boolean ok = admissionServices.updateAdmission(adm);
        if (ok) {
            System.out.println("Admission updated successfully.");
        } else {
            System.out.println("Failed to update admission.");
        }
    }

    private static void dischargePatient() {
        int admissionId = readInt("Enter Admission ID: ");
        Admission adm = admissionServices.getAdmissionById(admissionId);
        if (adm != null && "Admitted".equalsIgnoreCase(adm.getStatus())) {
        boolean discharged = admissionServices.dischargePatient(admissionId, LocalDate.now());;
            if (discharged) {
                if (adm.getBed() != null) {
                    Bed b = adm.getBed();
                    b.setOccupied(false);
                    bedService.updateBed(b);
                }
                System.out.println("Patient discharged successfully.");
            } else {
                System.out.println("Failed to discharge patient.");
            }
        } else {
            System.out.println("Active admission not found for ID: " + admissionId);
        }
    }

    private static void viewActiveAdmissions() {
        List<Admission> all = admissionServices.getAllAdmissions();
        List<Admission> active = new ArrayList<>();
        for (Admission a : all) {
            if ("Admitted".equalsIgnoreCase(a.getStatus())) {
                active.add(a);
            }
        }
        admissionView.displayAdmissions(active);
    }

    // --- WARD MANAGEMENT MENU ---
    private static void wardManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Ward Management ---");
            System.out.println("1. Add Ward");
            System.out.println("2. View All Wards");
            System.out.println("3. Find Ward");
            System.out.println("4. Update Ward");
            System.out.println("5. Delete Ward");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    System.out.print("Ward Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Ward Type: ");
                    String type = scanner.nextLine().trim();
                    int cap = readInt("Capacity: ");
                    Ward w = new Ward();
                    w.setName(name);
                    w.setWardType(type);
                    w.setCapacity(cap);
                    wardService.addWard(w);
                    System.out.println("Ward added successfully.");
                    break;
                case 2:
                    wardView.displayWards(wardService.getAllWards());
                    break;
                case 3:
                    int wid = readInt("Enter Ward ID: ");
                    wardView.displayWard(wardService.getWardById(wid));
                    break;
                case 4:
                    int uid = readInt("Enter Ward ID to Update: ");
                    Ward uw = wardService.getWardById(uid);
                    if (uw != null) {
                        System.out.print("Name [" + uw.getName() + "]: ");
                        String n = scanner.nextLine().trim();
                        if (!n.isEmpty()) uw.setName(n);
                        System.out.print("Type [" + uw.getWardType() + "]: ");
                        String t = scanner.nextLine().trim();
                        if (!t.isEmpty()) uw.setWardType(t);
                        wardService.updateWard(uw);
                        System.out.println("Ward updated.");
                    } else {
                        System.out.println("Ward not found.");
                    }
                    break;
                case 5:
                    int did = readInt("Enter Ward ID to Delete: ");
                    wardService.deleteWard(did);
                    System.out.println("Ward deleted.");
                    break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- ROOM MANAGEMENT MENU ---
    private static void roomManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Room Management ---");
            System.out.println("1. Add Room");
            System.out.println("2. View All Rooms");
            System.out.println("3. Find Room");
            System.out.println("4. Update Room");
            System.out.println("5. Delete Room");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    System.out.print("Room Number: ");
                    String rNum = scanner.nextLine().trim();
                    System.out.print("Room Type: ");
                    String rType = scanner.nextLine().trim();
                    int wid = readInt("Ward ID: ");
                    Ward ward = wardService.getWardById(wid);
                    Room r = new Room();
                    r.setRoomNumber(rNum);
                    r.setRoomType(rType);
                    r.setWard(ward);
                    roomService.addRoom(r);
                    System.out.println("Room added successfully.");
                    break;
                case 2:
                    roomView.displayRooms(roomService.getAllRooms());
                    break;
                case 3:
                    int rid = readInt("Enter Room ID: ");
                    roomView.displayRoom(roomService.getRoomById(rid));
                    break;
                case 4:
                    int uid = readInt("Enter Room ID to Update: ");
                    Room ur = roomService.getRoomById(uid);
                    if (ur != null) {
                        System.out.print("Room Number [" + ur.getRoomNumber() + "]: ");
                        String rn = scanner.nextLine().trim();
                        if (!rn.isEmpty()) ur.setRoomNumber(rn);
                        System.out.print("Room Type [" + ur.getRoomType() + "]: ");
                        String rt = scanner.nextLine().trim();
                        if (!rt.isEmpty()) ur.setRoomType(rt);
                        roomService.updateRoom(ur);
                        System.out.println("Room updated.");
                    } else {
                        System.out.println("Room not found.");
                    }
                    break;
                case 5:
                    int did = readInt("Enter Room ID to Delete: ");
                    roomService.deleteRoom(did);
                    System.out.println("Room deleted.");
                    break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- BED MANAGEMENT MENU ---
    private static void bedManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Bed Management ---");
            System.out.println("1. Add Bed");
            System.out.println("2. View All Beds");
            System.out.println("3. Find Bed");
            System.out.println("4. Update Bed");
            System.out.println("5. Delete Bed");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    System.out.print("Bed Number: ");
                    String bNum = scanner.nextLine().trim();
                    int rid = readInt("Room ID: ");
                    Room room = roomService.getRoomById(rid);
                    Bed b = new Bed();
                    b.setBedNumber(bNum);
                    b.setRoom(room);
                    b.setOccupied(false);
                    bedService.addBed(b);
                    System.out.println("Bed added successfully.");
                    break;
                case 2:
                    bedView.displayBeds(bedService.getAllBeds());
                    break;
                case 3:
                    int bid = readInt("Enter Bed ID: ");
                    bedView.displayBed(bedService.getBedById(bid));
                    break;
                case 4:
                    int uid = readInt("Enter Bed ID to Update: ");
                    Bed ub = bedService.getBedById(uid);
                    if (ub != null) {
                        System.out.print("Bed Number [" + ub.getBedNumber() + "]: ");
                        String bn = scanner.nextLine().trim();
                        if (!bn.isEmpty()) ub.setBedNumber(bn);
                        bedService.updateBed(ub);
                        System.out.println("Bed updated.");
                    } else {
                        System.out.println("Bed not found.");
                    }
                    break;
                case 5:
                    int did = readInt("Enter Bed ID to Delete: ");
                    bedService.deleteBed(did);
                    System.out.println("Bed deleted.");
                    break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewAvailableBeds() {
        bedView.displayBeds(bedService.getAvailableBeds());
    }

    private static void viewOccupiedBeds() {
        bedView.displayBeds(bedService.getOccupiedBeds());
    }

    // --- NURSE ASSIGNMENT MENU ---
    private static void nurseAssignmentMenu() {
        System.out.println("\n--- Nurse Assignment ---");
        int nurseId = readInt("Enter Nurse Staff ID: ");
        Nurse nurse = nurseService.getNurseById(nurseId);
        if (nurse == null) {
            System.out.println("Nurse not found.");
            return;
        }

        int admId = readInt("Enter Admission ID: ");
        Admission adm = admissionServices.getAdmissionById(admId);
        if (adm == null) {
            System.out.println("Admission not found.");
            return;
        }

        System.out.print("Enter Shift (e.g., Morning/Night): ");
        String shift = scanner.nextLine().trim();

        NurseAssignment na = new NurseAssignment();
        na.setNurse(nurse);
        na.setAdmission(adm);
        na.setPatient(adm.getPatient());
        na.setAssignmentDate(LocalDateTime.now());
        na.setShift(shift);

        System.out.println("Nurse " + nurse.getFirstName() + " assigned to Admission #" + adm.getId() + " successfully.");
    }
    
    
    
    // =========================================================
    // INPUT UTILITIES
    // =========================================================
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Try again.");
            }
        }
    }

    private static double readDouble(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid decimal number (e.g., 45000.00).");
        }
    }
    }
}