package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testStringMgrClass
{

    public String hl7Message =
                                     "MSH|^~\\&|LAB|51219228|||201001161020||ORU^R01|Z7-PSORYA.1.7752|T|2.3\r"
                                             + "PID|1||9999999||DOE^JOE|BOUVIER,MARIE|19680203|M|||1534 DORION^^MONTREAL^QC^NIL||514 265-9877|||3||O000030/09|XXXX 0000 0000\r"
                                             + "PV1|1|O|HDURO||||59238^CHAREST CHAMPLAIN       (RRAD)^^CHAR C|||||||||||REF||U|||||||||||||||||||HD||REG|||200907211249\r"
                                             + "OBR|1|98205^20101601:B00007R^4^064579||GLU^GLUCOSE^130.0500^G||201001161019|20100116|||LABPELL||||201001161020|CPND|59238^CHAREST CHAMPLAIN       (RRAD)^^CHAR C||||||201001161019||BIO^BIOCHIMIE|RECD||^^^^^R|||||LABPELL|LABPELL\r"
                                             + "PID|2||9999999||DOE^JOE|BOUVIER,MARIE|19680203|M|||1534 DORION^^MONTREAL^QC^NIL||514 265-9877|||3||O000030/09|XXXX 0000 0000\r"
                                             + "OBX|1|ST|ICT^ICTERE^130.0051^ICTERE^ICTERE^B|130.0500|||||||I||||||^ND^A^ICTERE\r"
                                             + "OBX|2|ST|LIPE^LIPEMIE^130.0101^LIPEMIE^LIPEMIE^B|130.0500|||||||I||||||^ND^A^LIPEMIE\r"
                                             + "OBX|3|ST|HEMOL^HEMOLYSE^130.0151^HEMOLYSE^HEMOLYSE^B|130.0500|||||||I||||||^ND^B^HEMOLYSE\r"
                                             + "OBX|4|NM|GLUZ^GLUCOSE^150.0201^GLUCOSE^GLUCOSE^B|130.0500||mmol/L|4.0-6.2^4.0^6.2||||I||||||^ND^G^GLUCOSE\r"
                                             + "ZBX|1|NM|GLUZ^GLUCOSE^150.0201^GLUCOSE^GLUCOSE^B|130.0500||mmol/L|4.0-6.2^4.0^6.2||||I||||||^ND^G^GLUCOSE\r"
                                             + "OBX|4|NM|GLUZ^GLUCOSE^150.0201^GLUCOSE^GLUCOSE^B|130.0500||mmol/L|4.0-6.2^4.0^6.2||||I||||||^ND^G^GLUCOSE\r"
                                             + "ZBX|2|NM|GLUZ^GLUCOSE^150.0201^GLUCOSE^GLUCOSE^B|130.0500||mmol/L|4.0-6.2^4.0^6.2||||I||||||^ND^G^GLUCOSE";

    public void stripString()
    {

        // PipeParser pipeParser = new PipeParser();
        // pipeParser.setValidationContext(new NoValidation());
        // Message hl7Message = pipeParser.parse(strhl7Message);
        //
        // Terser terser = new Terser(hl7Message);
        // System.out.println("Terser PID line:\n" + terser.get("PID").toString());
        //
        // String apiPIDstr = ((ORU_R01)hl7Message).getRESPONSE().getPATIENT().getPID().getMessage().toString();
        // System.out.println("ORU PID line:\n" + apiPIDstr.toString());
        // String hl7MessageWithoutPID = strhl7Message.replace(apiPIDstr, "");

        System.out.println("Message In:\n" + hl7Message);

        hl7Message = hl7Message.replaceAll("PID.+(\r|\n|$)",
                                           "");
        hl7Message = hl7Message.replaceAll("ZBX.+(\r|\n|$)",
                                           "");
        // int PIDindexBegin = hl7Message.indexOf("PID");
        // int PIDindexEnd = hl7Message.indexOf("\r",
        // PIDindexBegin);
        //
        // System.out.println("PID begin index:" + PIDindexBegin);
        // System.out.println("PID end index:" + PIDindexEnd);
        //
        // StringBuilder sbHl7MessageWithoutPID = new StringBuilder();
        // sbHl7MessageWithoutPID.append(hl7Message.substring(0,
        // PIDindexBegin));
        // sbHl7MessageWithoutPID.append(hl7Message.substring(PIDindexEnd + 1,
        // hl7Message.length()));
        // String hl7MessageWithoutPID = sbHl7MessageWithoutPID.toString();
        System.out.println("Message Out:\n" + hl7Message);

    }

    public void matchesString()
    {

        String regex = "(.*DROP.*)|(.*TRUNCATE.*)";

        Pattern sqlBannedWordPattern = Pattern.compile(regex,
                                                       Pattern.DOTALL + Pattern.CASE_INSENSITIVE);

        String[] sqlSelectTestList =
                new String[] { "Select * from Drop tables USERS and mouahaha", "DROP TABLES USERS",
                        "I will truncate you all", "Select * from HL7_Message_Received_log",
                        "Select * from HL7_Message_Received_log; TRUNCATE TABLES", "droptruncateandmatch",
                        "select *\nDROP BACON", "\nTrun\ncate USERS", "     I will d\trop you",
                        "\r\n\tselect * from\t drolp \rtruncating", "drOp dead", "trunCATe test" };

        Matcher sqlMatcher = null;

        System.out.println("REGEX: " + regex);
        for (String sqlSelectEntry : sqlSelectTestList)
        {
            sqlMatcher = sqlBannedWordPattern.matcher(sqlSelectEntry);
            System.out.println("Matches?: " + sqlMatcher.matches() + " msg: " + sqlSelectEntry);
        }
    }

    public static void main(String[] args)
    {
        testStringMgrClass testStringMgrClass = new testStringMgrClass();
        try
        {
            testStringMgrClass.matchesString();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }
}
