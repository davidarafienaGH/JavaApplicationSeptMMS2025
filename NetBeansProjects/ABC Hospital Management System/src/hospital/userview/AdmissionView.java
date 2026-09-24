package hospital.userview;

import hospital.models.Admission;
import hospital.models.Bed;

import java.util.List;

public class AdmissionView {

    public void displayAdmission(Admission admission) {
        if (admission == null) {
            System.out.println("Admission not found.");
            return;
        }

        System.out.println(".==============================================");
        System.out.println("                    ADMISSION DETAILS");
        System.out.println("===============================================");
        System.out.println("Admission ID   : " + admission.getId());
        System.out.println("Patient ID     : " + (admission.getPatient() != null ? admission.getPatient().getPatientId() : "N/A"));
        System.out.println("Bed ID         : " + (admission.getBed() != null ? admission.getBed().getId() : "N/A"));
        System.out.println("Admission Date : " + String.valueOf(admission.getAdmissionDate()));
        System.out.println("Discharge Date : " + (admission.getDischargeDate() != null ? admission.getDischargeDate().toString() : "Not discharged"));
        System.out.println("Reason         : " + admission.getReason());
        System.out.println("Status         : " + admission.getStatus());
        System.out.println("===============================================");
    }

    public void displayAdmissions(List<Admission> admissions) {
        if (admissions == null || admissions.isEmpty()) {
            System.out.println("No admissions found.");
            return;
        }

        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-12s %-8s %-15s %-15s %-20s %-15s%n",
                "ADM ID", "PATIENT ID", "BED ID", "ADMIT DATE", "DISCHARGE", "REASON", "STATUS");
        System.out.println("==================================================================================================");

        for (Admission admission : admissions) {
            String discharge = (admission.getDischargeDate() != null)
                    ? admission.getDischargeDate().toString()
                    : "Not discharged";

            System.out.printf("%-10d %-12d %-8d %-15s %-15s %-20s %-15s%n",
                    admission.getId(),
                    admission.getPatient() != null ? admission.getPatient().getPatientId() : 0,
                    admission.getBed() != null ? admission.getBed().getId() : 0,
                    String.valueOf(admission.getAdmissionDate()),
                    discharge,
                    admission.getReason(),
                    admission.getStatus());
        }
        System.out.println("==================================================================================================");
    }

    public void displayAvailableBeds(List<Bed> beds) {
        if (beds == null || beds.isEmpty()) {
            System.out.println("No available beds.");
            return;
        }

        System.out.println("============================================================================");
        System.out.println("                        AVAILABLE BEDS");
        System.out.println("============================================================================");
        System.out.printf("%-8s %-12s %-12s %-15s %-20s%n",
                "BED NO", "ROOM", "ROOM TYPE", "WARD", "STATUS");
        System.out.println("============================================================================");

        for (Bed bed : beds) {
            String roomNumber = (bed.getRoom() != null) ? bed.getRoom().getRoomNumber() : "N/A";
            String roomType = (bed.getRoom() != null) ? bed.getRoom().getRoomType() : "N/A";
            String wardName = (bed.getRoom() != null && bed.getRoom().getWard() != null)
                    ? bed.getRoom().getWard().getName()
                    : "N/A";

            System.out.printf("%-8d %-12s %-12s %-15s %-20s%n",
                    bed.getBedNumber(),
                    roomNumber,
                    roomType,
                    wardName,
                    "Available");
        }
        System.out.println("============================================================================");
    }
}