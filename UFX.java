import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class UFX {
    static final String msgIdStart = "AAA-555-333-EEE-_";
    static final String appRegNumberStart = "REG_NEW_CLIENT_REQ_";
    static  final String clientRegNumberStart = "REG_NEW_CLIENT_";
    static final String SubAppRegNumberStart = "REG_NEW_CONTRACT_";

    /*
     * @param printStream Stream for output.
     * @param shortName First name and surname, glued.
     * @param login Login used for registration.
     * @param msgIdEnd .Part of message identifier. Should be unique.
     * @param appRegNumberEnd Part of application identifier. Must be unique.
     * @param clientRegNumberEnd Part of client registration identifier. Must be unique.
     * @param subAppRegNumberEnd Part of contract registration identifier. Must be unique.
     * @return Xml text string.
     */
    public static String generateLessorRegisterRequest(String shortName,
                                                       String login,
                                                       String dateOpen,
                                                       int msgIdEnd,
                                                       int appRegNumberEnd,
                                                       int clientRegNumberEnd,
                                                       int subAppRegNumberEnd) {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(os);

        XmlBlock Product = new XmlBlock("Product");
        Product.addChild("ProductCode1", "LESSOR_W");

        XmlBlock ContractIDT = new XmlBlock("ContractIDT");
        ContractIDT.addChild("ContractNumber");

        XmlBlock Contract = new XmlBlock("Contract");
        Contract.addChild(ContractIDT);
        Contract.addChild(Product);
        Contract.addChild("DateOpen", dateOpen);

        XmlBlock ContractData = new XmlBlock("Data");
        ContractData.addChild(Contract);

        XmlBlock SubApp = new XmlBlock("Application");
        SubApp.addChild("RegNumber", SubAppRegNumberStart + subAppRegNumberEnd);
        subAppRegNumberEnd++;
        SubApp.addChild("ObjectType", "Contract");
        SubApp.addChild("ActionType", "Add");
        SubApp.addChild(ContractData);

        XmlBlock SubAppList = new XmlBlock("SubApplList");
        SubAppList.addChild(SubApp);

        XmlBlock ClientInfo = new XmlBlock("ClientInfo");
        ClientInfo.addChild("ClientNumber", login + "_LESSOR");
        ClientInfo.addChild("RegNumber",
                clientRegNumberStart + clientRegNumberEnd + "_" + login + "_LESSOR");
        ClientInfo.addChild("ShortName", shortName);

        clientRegNumberEnd++;

        XmlBlock Client = new XmlBlock("Client");
        Client.addChild("ClientType", "M_RES");
        Client.addChild(ClientInfo);

        XmlBlock Data = new XmlBlock("Data");
        Data.addChild(Client);

        XmlBlock Parm1 = new XmlBlock("Parm");
        Parm1.addChild("ParmCode", "AcceptRq");
        Parm1.addChild("Value", "Y");

        XmlBlock Parm2 = new XmlBlock("Parm");
        Parm2.addChild("ParmCode", "Response");
        Parm2.addChild("Value", "Y");

        XmlBlock ResultDtls = new XmlBlock("ResultDtls");
        ResultDtls.addChild(Parm1);
        ResultDtls.addChild(Parm2);

        XmlBlock Application = new XmlBlock("Application");
        Application.addChild("RegNumber", appRegNumberStart + appRegNumberEnd);
        appRegNumberEnd++;
        Application.addChild("Institution", "0001");
        Application.addChild("OrderDprt", "0101");
        Application.addChild("ObjectType", "Client");
        Application.addChild("ActionType", "Add");
        Application.addChild(ResultDtls);
        Application.addChild("ProductGroup", "ACQRET");
        Application.addChild(Data);
        Application.addChild(SubAppList);

        XmlBlock MsgData = new XmlBlock("MsgData");
        MsgData.addChild(Application);

        XmlBlock UFXMsg = new XmlBlock("UFXMsg");
        UFXMsg.addAttribute("scheme=\"WAY4Appl\"");
        UFXMsg.addAttribute("msg_type=\"Application\"");
        UFXMsg.addAttribute("version=\"2.0\"");
        UFXMsg.addAttribute("direction=\"Rq\"");
        UFXMsg.addChild("MsgId", msgIdStart + msgIdEnd);
        msgIdEnd++;
        UFXMsg.addChild("Source app=\"WebApp\"");
        UFXMsg.addChild(MsgData);

        UFXMsg.print(printStream, "");

        String output = null;
        try {
            output = os.toString("UTF8");
        } catch (UnsupportedEncodingException e) {

        }

        return output;

    }

    /*
     * @param printStream Stream for output.
     * @param shortName First name and surname, glued.
     * @param login Login used for registration.
     * @param dateOpen Date for registration. YYYY-MM-DD
     * @param msgIdEnd .Part of message identifier. Should be unique.
     * @param appRegNumberEnd Part of application identifier. Must be unique.
     * @param clientRegNumberEnd Part of client registration identifier. Must be unique.
     * @param subAppRegNumberEnd Part of contract registration identifier. Must be unique.
     * @return Xml text string.
     */
    public static String GenerateLesseeRegisterRequest(String shortName,
                                                     String login,
                                                     String dateOpen,
                                                     int msgIdEnd,
                                                     int appRegNumberEnd,
                                                     int clientRegNumberEnd,
                                                     int subAppRegNumberEnd) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(os);

        XmlBlock Product = new XmlBlock("Product");
        Product.addChild("ProductCode1", "LESSEE_W");

        XmlBlock ContractIDT = new XmlBlock("ContractIDT");
        ContractIDT.addChild("ContractNumber");

        XmlBlock Contract = new XmlBlock("Contract");
        Contract.addChild(ContractIDT);
        Contract.addChild(Product);

        XmlBlock ContractData = new XmlBlock("Data");
        ContractData.addChild(Contract);

        XmlBlock SubApp = new XmlBlock("Application");
        SubApp.addChild("RegNumber", SubAppRegNumberStart + subAppRegNumberEnd);
        subAppRegNumberEnd++;
        SubApp.addChild("ObjectType", "Contract");
        SubApp.addChild("ActionType", "Add");
        SubApp.addChild(ContractData);

        XmlBlock SubAppList = new XmlBlock("SubApplList");
        SubAppList.addChild(SubApp);

        XmlBlock ClientInfo = new XmlBlock("ClientInfo");
        ClientInfo.addChild("ClientNumber", login + "_LESSEE");
        ClientInfo.addChild("RegNumber",
                clientRegNumberStart + clientRegNumberEnd + "_" + login + "_LESSEE");
        ClientInfo.addChild("ShortName", shortName);

        clientRegNumberEnd++;

        XmlBlock Client = new XmlBlock("Client");
        Client.addChild("ClientType", "PR");
        Client.addChild(ClientInfo);

        XmlBlock Data = new XmlBlock("Data");
        Data.addChild(Client);

        XmlBlock Parm1 = new XmlBlock("Parm");
        Parm1.addChild("ParmCode", "AcceptRq");
        Parm1.addChild("Value", "Y");

        XmlBlock Parm2 = new XmlBlock("Parm");
        Parm2.addChild("ParmCode", "Response");
        Parm2.addChild("Value", "Y");

        XmlBlock ResultDtls = new XmlBlock("ResultDtls");
        ResultDtls.addChild(Parm1);
        ResultDtls.addChild(Parm2);

        XmlBlock Application = new XmlBlock("Application");
        Application.addChild("RegNumber", appRegNumberStart + appRegNumberEnd);
        appRegNumberEnd++;
        Application.addChild("Institution", "0001");
        Application.addChild("OrderDprt", "0101");
        Application.addChild("ObjectType", "Client");
        Application.addChild("ActionType", "Add");
        Application.addChild(ResultDtls);
        Application.addChild("ProductGroup", "ISSDEB");
        Application.addChild(Data);
        Application.addChild(SubAppList);

        XmlBlock MsgData = new XmlBlock("MsgData");
        MsgData.addChild(Application);

        XmlBlock UFXMsg = new XmlBlock("UFXMsg");
        UFXMsg.addAttribute("scheme=\"WAY4Appl\"");
        UFXMsg.addAttribute("msg_type=\"Application\"");
        UFXMsg.addAttribute("version=\"2.0\"");
        UFXMsg.addAttribute("direction=\"Rq\"");
        UFXMsg.addChild("MsgId", msgIdStart + msgIdEnd);
        msgIdEnd++;
        UFXMsg.addChild("Source app=\"WebApp\"");
        UFXMsg.addChild(MsgData);

        UFXMsg.print(printStream, "");

        String output = null;
        try {
            output = os.toString("UTF8");
        } catch (UnsupportedEncodingException e) {

        }

        return output;
    }

    /*
     * @param text Xml file.
     */
    public static Map<String, String> parseRegisterResponce(String text) {
        Map<String, String> stringStringMap = new TreeMap<>();

        int valueStart = text.indexOf("<MsgId>") + "<MsgId>".length();
        int valueEnd = text.indexOf("</MsgId>");
        if (valueStart != -1 && valueEnd != -1) {
            stringStringMap.put("MsgId", text.substring(valueStart, valueEnd));
        }

        valueStart = text.indexOf("<RespCode>") + "<RespCode>".length();
        valueEnd = text.indexOf("</RespCode>");
        if (valueStart != -1 && valueEnd != -1) {
            stringStringMap.put("RespCode", text.substring(valueStart, valueEnd));
        }

        valueStart = text.indexOf("<RespText>") + "<RespText>".length();
        valueEnd = text.indexOf("</RespText>");
        if (valueStart != -1 && valueEnd != -1) {
            stringStringMap.put("RespText", text.substring(valueStart, valueEnd));
        }

        valueStart = text.indexOf("<ContractNumber>") + "<ContractNumber>".length();
        valueEnd = text.indexOf("</ContractNumber>");
        if (valueStart != -1 && valueEnd != -1) {
            stringStringMap.put("ContractNumber", text.substring(valueStart, valueEnd));
        }

        return stringStringMap;
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    public static void main(String[] args) {
        int msgIdEnd = 0;
        int appRegNumberEnd = 0;
        int clientRegNumberEnd = 0;
        int subAppRegNumberEnd = 0;

        FileInputStream fileInputStream = null;
        Map<String, String> stringStringMap;
        try {
            fileInputStream = new FileInputStream("responce.xml");
            System.out.println(generateLessorRegisterRequest("User01", "login01", "2019-07-03",
                    msgIdEnd++, appRegNumberEnd++, clientRegNumberEnd++, subAppRegNumberEnd++));
            stringStringMap = parseRegisterResponce(convertStreamToString(fileInputStream));
            System.out.println(stringStringMap.get("RespCode"));
            System.out.println(stringStringMap.get("RespText"));
            System.out.println(stringStringMap.get("MsgId"));
        } catch (IOException e) {

        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {

            }
        }
    }
}
