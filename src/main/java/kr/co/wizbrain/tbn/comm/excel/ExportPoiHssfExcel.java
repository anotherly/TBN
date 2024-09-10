package kr.co.wizbrain.tbn.comm.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.wizbrain.tbn.comm.RecordDto;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.AbstractView;
/* loaded from: ExportPoiHssfExcel.class */
public class ExportPoiHssfExcel extends AbstractView {
    private static final Log log = LogFactory.getLog(ExportPoiHssfExcel.class);
    String filename;
    FileOutputStream fileout;

    public ExportPoiHssfExcel() {
        super.setContentType("application/octet-stream");
    }

    private String getFileName(Map<?, ?> model) {
        String name = (String) model.get("fileName");
        try {
            return URLEncoder.encode(name, "UTF-8").replace('+', ' ');
        } catch (UnsupportedEncodingException e) {
            return name;
        }
    }

    private List<String> getColumnTitles(Map<?, ?> model) {
        String[] titles = (String[]) model.get("columnTitles");
        List<String> list = new ArrayList<>();
        for (String str : titles) {
            list.add(str);
        }
        return list;
    }

    private List<List<String>> getExportthisMonth(Map<?, ?> model) {
        String[] names = (String[]) model.get("columnNames");
        List<?> thisMonth = (List) model.get("exportthisMonth");
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < thisMonth.size(); i++) {
            RecordDto record = (RecordDto) thisMonth.get(i);
            List<String> item = new ArrayList<>();
            for (String str : names) {
                item.add(record.getString(str));
            }
            list.add(item);
        }
        return list;
    }

    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Cache-Control", "no-cache");
            setContentType("application/vnd.ms-excel;charset=UTF-8");
            HSSFWorkbook wb = new HSSFWorkbook();
            this.filename = getFileName(model);
            if (model.get("mapping").equals("standardInformerType")) {
                standardInformerType(model, wb);
            } else if (model.get("mapping").equals("extrBro")) {
                extrBro(model, wb);
            } else if (model.get("mapping").equals("disastorStat")) {
                disastorStat(model, wb);
            } else if (model.get("mapping").equals("muJebo")) {
                muJebo(model, wb);
            } else if (model.get("mapping").equals("muJebo2")) {
                muJebo2(model, wb);
            } else if (model.get("mapping").equals("standardInformUse")) {
                standardInformUse(model, wb);
            } else if (model.get("mapping").equals("standartdReceiptUse")) {
                standartdReceiptUse(model, wb);
            } else if (model.get("mapping").equals("nationalIncident")) {
                nationalIncident(model, wb);
            } else if (model.get("mapping").equals("standardInformer")) {
                standardInformer(model, wb);
            } else if (model.get("mapping").equals("krGas")) {
                korLx(model, wb);
            } else if (model.get("mapping").equals("korLx")) {
                korLx(model, wb);
            } else if (model.get("mapping").equals("receiptDown")) {
                receiptDown(model, wb);
            } else if (model.get("mapping").equals("dayReceipt")) {
                dayReceipt(model, wb);
            } else if (model.get("mapping").equals("volunteer")) {
                volunteer(model, wb);
            } else if (model.get("mapping").equals("informerDown")) {
                informerDown(model, wb);
            }
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType(super.getContentType());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + this.filename + "\";");
            wb.write(outputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void standardInformerType(Map model, HSSFWorkbook wb) {
        List titleMain = (List) model.get("titleMain");
        List headMain = (List) model.get("headMain");
        List sumMain = (List) model.get("sumMain");
        List sumOurOtherMain = (List) model.get("sumOurOtherMain");
        List dataMain = (List) model.get("dataMain");
        for (int i = 0; i < titleMain.size(); i++) {
            informerType(wb, (String) titleMain.get(i), (List) headMain.get(i), (List) sumMain.get(i), (List) sumOurOtherMain.get(i), (List) dataMain.get(i));
        }
    }

    protected void informerType(HSSFWorkbook wb, String titleData, List headData, List headData1, List headData2, List data) {
        HSSFSheet sheet1 = wb.createSheet(titleData);
        String csType = "";
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue("교통정보 제공대장");
        titlerow.setHeight((short) 800);
        int rowCnt = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt);
        headrow.createCell(0).setCellValue(titleData);
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        headrow.createCell(2).setCellValue("계");
        for (int j = 0; j < headData.size(); j++) {
            headrow.createCell((j * 2) + 4).setCellValue(((RecordDto) headData.get(j)).getString("CODE_NAME"));
        }
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt2);
        int sum = 0;
        for (int j2 = 0; j2 < headData1.size(); j2++) {
            RecordDto record = (RecordDto) headData1.get(j2);
            headrow1.createCell((j2 * 2) + 4).setCellValue(record.getInt("CNT"));
            sum += record.getInt("CNT");
        }
        headrow1.createCell(0).setCellValue("수집건수");
        headrow1.createCell(2).setCellValue(sum);
        for (int j3 = 0; j3 < headData.size() + 2; j3++) {
            sheet1.addMergedRegion(new CellRangeAddress(rowCnt2 - 1, rowCnt2 - 1, j3 * 2, (j3 * 2) + 1));
            if (j3 == 0) {
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt2, rowCnt2 + 2, j3 * 2, (j3 * 2) + 1));
            } else {
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt2, rowCnt2, j3 * 2, (j3 * 2) + 1));
            }
        }
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow headrow12 = sheet1.createRow(rowCnt3);
        for (int j4 = 0; j4 < headData.size() + 1; j4++) {
            headrow12.createCell((j4 * 2) + 2).setCellValue("자국");
            headrow12.createCell((j4 * 2) + 3).setCellValue("타국");
        }
        int rowCnt4 = rowCnt3 + 1;
        HSSFRow headrow13 = sheet1.createRow(rowCnt4);
        int sumOur = 0;
        int sumOther = 0;
        for (int j5 = 0; j5 < headData2.size(); j5++) {
            RecordDto record2 = (RecordDto) headData2.get(j5);
            headrow13.createCell(j5 + 4).setCellValue(record2.getInt("CNT"));
            if (record2.getString("REGION_ID").equals("000")) {
                sumOther += record2.getInt("CNT");
            } else {
                sumOur += record2.getInt("CNT");
            }
        }
        headrow13.createCell(2).setCellValue(sumOur);
        headrow13.createCell(3).setCellValue(sumOther);
        int rowCnt5 = rowCnt4 + 1;
        DecimalFormat df = new DecimalFormat("##0.0%");
        for (int i = 0; i < data.size() / (headData.size() * 2); i++) {
            int i2 = rowCnt5;
            int rowCnt6 = rowCnt5 + 1;
            HSSFRow dataRow = sheet1.createRow(i2);
            rowCnt5 = rowCnt6 + 1;
            HSSFRow ratioRow = sheet1.createRow(rowCnt6);
            int sumOur2 = 0;
            int sumOther2 = 0;
            for (int j6 = 0; j6 < headData.size() * 2; j6++) {
                RecordDto record3 = (RecordDto) data.get((i * headData.size() * 2) + j6);
                if (j6 == 0) {
                    String TYPE = record3.getString("TYPE");
                    if (TYPE.contains("CASTER") || TYPE.contains("STUDIO")) {
                        csType = TYPE.split("_")[0];
                        dataRow.createCell(1).setCellValue(TYPE.split("_")[1]);
                    } else if (TYPE.equals("전송건수") || TYPE.equals("방송건수")) {
                        if (TYPE.equals("전송건수")) {
                            dataRow.createCell(0).setCellValue("중요제보");
                        } else {
                            ratioRow.createCell(1).setCellValue("방송비율");
                        }
                        dataRow.createCell(1).setCellValue(TYPE);
                    } else {
                        dataRow.createCell(0).setCellValue(TYPE);
                        dataRow.createCell(1).setCellValue("건수");
                        ratioRow.createCell(1).setCellValue("비율");
                    }
                }
                if (dataRow.getCell(0) != null && !dataRow.getCell(0).getStringCellValue().equals("방송") && !dataRow.getCell(0).getStringCellValue().equals("비방송")) {
                    dataRow.getCell(0).getStringCellValue().equals("중요제보");
                }
                dataRow.createCell(j6 + 4).setCellValue(record3.getInt("CNT"));
                if (!dataRow.getCell(1).getStringCellValue().equals("STUDIO") && !dataRow.getCell(1).getStringCellValue().equals("CASTER") && !dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
                    if (dataRow.getCell(0) != null) {
                        if (dataRow.getCell(0).getStringCellValue().equals("방송") || dataRow.getCell(0).getStringCellValue().equals("비방송")) {
                            if (headrow13.getCell(j6 + 4).getNumericCellValue() == 0.0d) {
                                ratioRow.createCell(j6 + 4).setCellValue("0.0%");
                            } else {
                                ratioRow.createCell(j6 + 4).setCellValue(df.format(record3.getDouble("CNT") / sheet1.getRow(7).getCell(j6 + 4).getNumericCellValue()));
                            }
                        } else if (headrow13.getCell(j6 + 4).getNumericCellValue() == 0.0d) {
                            ratioRow.createCell(j6 + 4).setCellValue("0.0%");
                        } else {
                            ratioRow.createCell(j6 + 4).setCellValue(df.format(record3.getDouble("CNT") / headrow13.getCell(j6 + 4).getNumericCellValue()));
                        }
                    } else if (headrow13.getCell(j6 + 4).getNumericCellValue() == 0.0d) {
                        ratioRow.createCell(j6 + 4).setCellValue("0.0%");
                    } else {
                        ratioRow.createCell(j6 + 4).setCellValue(df.format(record3.getDouble("CNT") / sheet1.getRow(15).getCell(j6 + 4).getNumericCellValue()));
                    }
                }
                if (record3.getString("REGION_ID").equals("000")) {
                    sumOther2 += record3.getInt("CNT");
                } else {
                    sumOur2 += record3.getInt("CNT");
                }
            }
            dataRow.createCell(2).setCellValue(sumOur2);
            dataRow.createCell(3).setCellValue(sumOther2);
            if (dataRow.getCell(1).getStringCellValue().equals("STUDIO") || dataRow.getCell(1).getStringCellValue().equals("CASTER") || dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
                rowCnt5--;
            } else if (dataRow.getCell(0) != null) {
                if (dataRow.getCell(0).getStringCellValue().equals("방송") || dataRow.getCell(0).getStringCellValue().equals("비방송")) {
                    if (headrow13.getCell(2).getNumericCellValue() == 0.0d) {
                        ratioRow.createCell(2).setCellValue("0.0%");
                    } else {
                        ratioRow.createCell(2).setCellValue(df.format(sumOur2 / sheet1.getRow(7).getCell(2).getNumericCellValue()));
                    }
                    if (headrow13.getCell(3).getNumericCellValue() == 0.0d) {
                        ratioRow.createCell(3).setCellValue("0.0%");
                    } else {
                        ratioRow.createCell(3).setCellValue(df.format(sumOther2 / sheet1.getRow(7).getCell(3).getNumericCellValue()));
                    }
                } else {
                    if (headrow13.getCell(2).getNumericCellValue() == 0.0d) {
                        ratioRow.createCell(2).setCellValue("0.0%");
                    } else {
                        ratioRow.createCell(2).setCellValue(df.format(sumOur2 / headrow13.getCell(2).getNumericCellValue()));
                    }
                    if (headrow13.getCell(3).getNumericCellValue() == 0.0d) {
                        ratioRow.createCell(3).setCellValue("0.0%");
                    } else {
                        ratioRow.createCell(3).setCellValue(df.format(sumOther2 / headrow13.getCell(3).getNumericCellValue()));
                    }
                }
            } else {
                if (headrow13.getCell(2).getNumericCellValue() == 0.0d) {
                    ratioRow.createCell(2).setCellValue("0.0%");
                } else {
                    ratioRow.createCell(2).setCellValue(df.format(sumOur2 / sheet1.getRow(15).getCell(2).getNumericCellValue()));
                }
                if (headrow13.getCell(3).getNumericCellValue() == 0.0d) {
                    ratioRow.createCell(3).setCellValue("0.0%");
                } else {
                    ratioRow.createCell(3).setCellValue(df.format(sumOther2 / sheet1.getRow(15).getCell(3).getNumericCellValue()));
                }
            }
            log.debug("★★★ " + (rowCnt5 - 1));
            log.debug("★★★ " + dataRow.getCell(1).getStringCellValue());
            if (csType.equals("방송") && dataRow.getCell(1).getStringCellValue().equals("CASTER")) {
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt5 - 4, rowCnt5 - 1, 0, 0));
            } else if (csType.equals("중요제보") && dataRow.getCell(1).getStringCellValue().equals("CASTER")) {
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt5 - 5, rowCnt5 - 1, 0, 0));
            } else if (dataRow.getCell(0) != null && !dataRow.getCell(0).getStringCellValue().equals("방송") && !dataRow.getCell(1).getStringCellValue().equals("STUDIO") && !dataRow.getCell(1).getStringCellValue().equals("방송건수") && !dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt5 - 2, rowCnt5 - 1, 0, 0));
            }
        }
    }

    protected void extrBro(Map model, HSSFWorkbook wb) {
        String inputDate;
        List list = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1 + 1 + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt);
        headrow1.createCell(0).setCellValue("일자");
        headrow1.createCell(1).setCellValue("총 긴급정보 건수");
        headrow1.createCell(2).setCellValue("5분내 방송처리 건수");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        int sum1 = 0;
        int sum2 = 0;
        List data = (List) model.get("Data");
        int rowCnt3 = rowCnt2 + 1;
        Calendar ca = Calendar.getInstance();
        String date = (String) model.get("start_date");
        int thYear = Integer.parseInt(date.substring(0, 4));
        int thMonth = Integer.parseInt(date.substring(4, 6));
        ca.set(thYear, 0, thMonth);
        int maxMon = ca.getActualMaximum(5);
        HSSFRow[] dataRow = new HSSFRow[maxMon];
        int[] iArr = new int[maxMon];
        int[] iArr2 = new int[maxMon];
        for (int i = 0; i < maxMon; i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            if (i < 9) {
                inputDate = date.concat("0").concat(Integer.toString(i + 1));
            } else {
                inputDate = date.concat(Integer.toString(i + 1));
            }
            dataRow[i].createCell(0).setCellValue(inputDate);
            int k = 0;
            while (true) {
                if (k >= data.size()) {
                    break;
                }
                RecordDto record = (RecordDto) data.get(k);
                if (inputDate.equals(record.getString("KEY_DATE"))) {
                    dataRow[i].createCell(1).setCellValue(record.getInt("CNT1"));
                    dataRow[i].createCell(2).setCellValue(record.getInt("CNT2"));
                    sum1 += record.getInt("CNT1");
                    sum2 += record.getInt("CNT2");
                    break;
                }
                dataRow[i].createCell(1).setCellValue(0.0d);
                dataRow[i].createCell(2).setCellValue(0.0d);
                k++;
            }
        }
        headrow2.createCell(0).setCellValue("계");
        headrow2.createCell(1).setCellValue(sum1);
        headrow2.createCell(2).setCellValue(sum2);
    }

    protected void disastorStat(Map model, HSSFWorkbook wb) {
        String inputDate;
        List list = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1 + 1 + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt);
        headrow1.createCell(0).setCellValue("일자");
        headrow1.createCell(1).setCellValue("일별 총 제보건수");
        headrow1.createCell(2).setCellValue("재난제보 건수");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        int sum1 = 0;
        int sum2 = 0;
        List data = (List) model.get("Data");
        int rowCnt3 = rowCnt2 + 1;
        Calendar ca = Calendar.getInstance();
        String date = (String) model.get("start_date");
        int thYear = Integer.parseInt(date.substring(0, 4));
        int thMonth = Integer.parseInt(date.substring(4, 6));
        ca.set(thYear, 0, thMonth);
        int maxMon = ca.getActualMaximum(5);
        HSSFRow[] dataRow = new HSSFRow[maxMon];
        int[] iArr = new int[maxMon];
        int[] iArr2 = new int[maxMon];
        for (int i = 0; i < maxMon; i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            if (i < 9) {
                inputDate = date.concat("0").concat(Integer.toString(i + 1));
            } else {
                inputDate = date.concat(Integer.toString(i + 1));
            }
            dataRow[i].createCell(0).setCellValue(inputDate);
            int k = 0;
            while (true) {
                if (k >= data.size()) {
                    break;
                }
                RecordDto record = (RecordDto) data.get(k);
                if (inputDate.equals(record.getString("KEY_DATE"))) {
                    dataRow[i].createCell(1).setCellValue(record.getInt("CNT1"));
                    dataRow[i].createCell(2).setCellValue(record.getInt("CNT2"));
                    sum1 += record.getInt("CNT1");
                    sum2 += record.getInt("CNT2");
                    break;
                }
                dataRow[i].createCell(1).setCellValue(0.0d);
                dataRow[i].createCell(2).setCellValue(0.0d);
                k++;
            }
        }
        headrow2.createCell(0).setCellValue("계");
        headrow2.createCell(1).setCellValue(sum1);
        headrow2.createCell(2).setCellValue(sum2);
    }

    protected void muJebo(Map model, HSSFWorkbook wb) {
        String j2;
        List headListMdata = (List) model.get("headList");
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        sheet1.createRow(rowCnt);
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        headrow2.createCell(0).setCellValue("ID");
        headrow2.createCell(1).setCellValue("이 름");
        headrow2.createCell(2).setCellValue("유형");
        headrow2.createCell(3).setCellValue("소속");
        headrow2.createCell(4).setCellValue("전화번호");
        headrow2.createCell(5).setCellValue("가입일자");
        for (int k = 1; k <= 12; k++) {
            headrow2.createCell(5 + k).setCellValue(String.valueOf(k) + "월");
        }
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow1 = new HSSFRow[headListMdata.size()];
        int[] monArr = new int[12];
        for (int i = 0; i < dataRow1.length; i++) {
            RecordDto record = (RecordDto) headListMdata.get(i);
            dataRow1[i] = sheet1.createRow(rowCnt3 + i);
            dataRow1[i].createCell(0).setCellValue(record.getString("ACT_ID"));
            dataRow1[i].createCell(1).setCellValue(record.getString("INFORMER_NAME"));
            dataRow1[i].createCell(2).setCellValue("통신원");
            dataRow1[i].createCell(3).setCellValue(record.getString("ORG_NAME"));
            dataRow1[i].createCell(4).setCellValue(record.getString("PHONE_CELL"));
            dataRow1[i].createCell(5).setCellValue(record.getString("REG_DATE"));
            for (int k2 = 0; k2 < dataList.size(); k2++) {
                RecordDto record2 = (RecordDto) dataList.get(k2);
                for (int j = 1; j <= 12; j++) {
                    if (j < 10) {
                        j2 = "0" + Integer.toString(j);
                    } else {
                        j2 = Integer.toString(j);
                    }
                    if (record.getString("INFORMER_ID").equals(record2.getString("INFORMER_ID"))) {
                        int inputNum = 0;
                        if (record2.getString("KEY_DATE").equals(j2)) {
                            inputNum = record2.getInt("CNT");
                            if (dataRow1[i].getCell(5 + j) == null) {
                                dataRow1[i].createCell(5 + j).setCellValue(inputNum);
                            }
                        }
                        monArr[j - 1] = monArr[j - 1] + inputNum;
                    }
                }
            }
        }
    }

    protected void muJebo2(Map model, HSSFWorkbook wb) {
        List headListMdata = (List) model.get("headList");
        List list = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt);
        headrow.createCell(0).setCellValue("총 인원 : " + headListMdata.size() + "명");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        headrow2.createCell(0).setCellValue("ID");
        headrow2.createCell(1).setCellValue("이 름");
        headrow2.createCell(2).setCellValue("유형");
        headrow2.createCell(3).setCellValue("소속");
        headrow2.createCell(4).setCellValue("전화번호");
        headrow2.createCell(5).setCellValue("가입일자");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow1 = new HSSFRow[headListMdata.size()];
        int[] iArr = new int[12];
        for (int i = 0; i < dataRow1.length; i++) {
            RecordDto record = (RecordDto) headListMdata.get(i);
            dataRow1[i] = sheet1.createRow(rowCnt3 + i);
            dataRow1[i].createCell(0).setCellValue(record.getString("ACT_ID"));
            dataRow1[i].createCell(1).setCellValue(record.getString("INFORMER_NAME"));
            dataRow1[i].createCell(2).setCellValue("통신원");
            dataRow1[i].createCell(3).setCellValue(record.getString("ORG_NAME"));
            dataRow1[i].createCell(4).setCellValue(record.getString("PHONE_CELL"));
            dataRow1[i].createCell(5).setCellValue(record.getString("REG_DATE"));
        }
    }

    protected void standardInformUse(Map model, HSSFWorkbook wb) {
        List sheetNames = (List) model.get("sheetNames");
        List monthReceiptMain = (List) model.get("monthReceipt");
        List monthOurReceiptMain = (List) model.get("monthOurReceiptMain");
        List monthOtherReceiptMain = (List) model.get("monthOtherReceiptMain");
        List monthInformerMain = (List) model.get("monthInformerMain");
        List monthInformer1InformMain = (List) model.get("monthInformer1InformMain");
        String thDateTime = (String) model.get("start_date");
        for (int i = 0; i < sheetNames.size(); i++) {
            informUse(wb, (String) sheetNames.get(i), (List) monthReceiptMain.get(i), (List) monthOurReceiptMain.get(i), (List) monthOtherReceiptMain.get(i), (List) monthInformerMain.get(i), (List) monthInformer1InformMain.get(i), thDateTime);
        }
    }

    protected void informUse(HSSFWorkbook wb, String sheetNames, List monthReceiptMain, List monthOurReceiptMain, List monthOtherReceiptMain, List monthInformerMain, List monthInformer1InformMain, String thDateTime) {
        HSSFSheet sheet1 = wb.createSheet(sheetNames);
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue("통신원 제보건수 및 접수요원 가공건수");
        int rowCnt = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt);
        int rowCnt2 = rowCnt + 1;
        HSSFRow rbRow = sheet1.createRow(rowCnt2);
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow rbRow1 = sheet1.createRow(rowCnt3);
        headrow.createCell(1).setCellValue("총 제보건수");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 2, rowCnt3 - 2, 1, 2));
        headrow.createCell(3).setCellValue(sheetNames);
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 2, rowCnt3 - 2, 3, 8));
        rbRow.createCell(1).setCellValue("월간");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 1, 1));
        rbRow.createCell(2).setCellValue("일간");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 2, 2));
        rbRow.createCell(3).setCellValue("제보건수(건)");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3 - 1, 3, 4));
        rbRow.createCell(5).setCellValue("총인원(명)");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 5, 5));
        rbRow.createCell(6).setCellValue("1인당 제보건수(총인원 대비)");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 6, 6));
        rbRow.createCell(7).setCellValue("1건이상 제보자수");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 7, 7));
        rbRow.createCell(8).setCellValue("1인당 제보건수(1건이상 제보자대비)");
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3, 8, 8));
        rbRow1.createCell(3).setCellValue("자국");
        rbRow1.createCell(4).setCellValue("타국");
        int rowCnt4 = rowCnt3 + 1;
        HSSFRow[] dataRow = new HSSFRow[12];
        int[] maxDay = new int[12];
        for (int i = 0; i < 12; i++) {
            dataRow[i] = sheet1.createRow(rowCnt4);
            dataRow[i].createCell(0).setCellValue(String.valueOf(i + 1) + "월");
            Calendar ca = Calendar.getInstance();
            int thYear = Integer.parseInt(thDateTime.substring(0, 4));
            int thMonth = Integer.parseInt(thDateTime.substring(4, 6));
            ca.set(thYear, i, thMonth);
            maxDay[i] = ca.getActualMaximum(5);
            rowCnt4++;
        }
        DecimalFormat df = new DecimalFormat("##0.0");
        for (int i2 = 0; i2 < monthReceiptMain.size(); i2++) {
            RecordDto record = (RecordDto) monthReceiptMain.get(i2);
            int statDate = Integer.parseInt(record.getString("STAT_MONTH").substring(4, 6));
            for (int j = 0; j < 12; j++) {
                if (j == statDate - 1) {
                    dataRow[statDate - 1].createCell(1).setCellValue(record.getInt("CNT"));
                    dataRow[statDate - 1].createCell(2).setCellValue(df.format(record.getDouble("CNT") / maxDay[j]));
                } else {
                    dataRow[j].createCell(1).setCellValue(0.0d);
                    dataRow[j].createCell(2).setCellValue(0.0d);
                }
            }
        }
        setRowData(monthOurReceiptMain, 12, dataRow, 3);
        setRowData(monthOtherReceiptMain, 12, dataRow, 4);
        for (int i3 = 0; i3 < monthInformerMain.size(); i3++) {
            RecordDto record2 = (RecordDto) monthInformerMain.get(i3);
            int statDate2 = Integer.parseInt(record2.getString("STAT_MONTH").substring(4, 6));
            for (int j2 = 0; j2 < 12; j2++) {
                if (j2 == statDate2 - 1) {
                    dataRow[statDate2 - 1].createCell(5).setCellValue(record2.getInt("CNT"));
                    dataRow[statDate2 - 1].createCell(6).setCellValue(df.format(dataRow[statDate2 - 1].getCell(1).getNumericCellValue() / record2.getDouble("CNT")));
                } else {
                    dataRow[j2].createCell(5).setCellValue(0.0d);
                    dataRow[j2].createCell(6).setCellValue(0.0d);
                }
            }
        }
        for (int i4 = 0; i4 < monthInformer1InformMain.size(); i4++) {
            RecordDto record3 = (RecordDto) monthInformer1InformMain.get(i4);
            int statDate3 = Integer.parseInt(record3.getString("STAT_MONTH").substring(4, 6));
            for (int j3 = 0; j3 < 12; j3++) {
                if (j3 == statDate3 - 1) {
                    dataRow[statDate3 - 1].createCell(7).setCellValue(record3.getInt("CNT"));
                    dataRow[statDate3 - 1].createCell(8).setCellValue(df.format(dataRow[statDate3 - 1].getCell(1).getNumericCellValue() / record3.getDouble("CNT")));
                } else {
                    dataRow[j3].createCell(7).setCellValue(0.0d);
                    dataRow[j3].createCell(8).setCellValue(0.0d);
                }
            }
        }
    }

    protected void setRowData(List<?> data, int cnt, HSSFRow[] dataRow, int cellIndex) {
        for (int i = 0; i < data.size(); i++) {
            RecordDto record = (RecordDto) data.get(i);
            int statDate = Integer.parseInt(record.getString("STAT_MONTH").substring(4, 6));
            for (int j = 0; j < cnt; j++) {
                if (j == statDate - 1) {
                    dataRow[statDate - 1].createCell(cellIndex).setCellValue(record.getInt("CNT"));
                } else {
                    dataRow[j].createCell(cellIndex).setCellValue(0.0d);
                }
            }
        }
    }

    protected void standartdReceiptUse(Map model, HSSFWorkbook wb) {
        String thDateTime = (String) model.get("start_date");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue("교통정보 수집건수 및 활용실적");
        int rowCnt = 0 + 1;
        HSSFRow mainRow = sheet1.createRow(rowCnt);
        mainRow.createCell(rowCnt).setCellValue(model.get("sheetNames1").toString());
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt2);
        headrow.createCell(0).setCellValue("구분");
        headrow.createCell(1).setCellValue("계");
        List headData = (List) model.get("headList");
        for (int j = 0; j < headData.size(); j++) {
            headrow.createCell((j * 2) + 3).setCellValue(((RecordDto) headData.get(j)).getString("CODE_NAME"));
        }
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt3);
        List headData1 = (List) model.get("useAllList");
        int sum = 0;
        for (int j2 = 0; j2 < headData1.size(); j2++) {
            RecordDto record = (RecordDto) headData1.get(j2);
            headrow1.createCell((j2 * 2) + 3).setCellValue(record.getInt("CNT"));
            sum += record.getInt("CNT");
        }
        headrow1.createCell(1).setCellValue(sum);
        int i = 0;
        while (i < headData.size() + 1) {
            sheet1.addMergedRegion(new CellRangeAddress(rowCnt3 - 1, rowCnt3 - 1, (i * 2) + 1, (i * 2) + 2));
            sheet1.addMergedRegion(new CellRangeAddress(rowCnt3, rowCnt3, (i * 2) + 1, (i * 2) + 2));
            i++;
        }
        int rowCnt4 = rowCnt3 + 1;
        HSSFRow headrow12 = sheet1.createRow(rowCnt4);
        for (int j3 = 0; j3 < headData.size() + 1; j3++) {
            headrow12.createCell((j3 * 2) + 1).setCellValue("자국");
            headrow12.createCell((j3 * 2) + 2).setCellValue("타국");
        }
        int rowCnt5 = rowCnt4 + 1;
        HSSFRow headrow13 = sheet1.createRow(rowCnt5);
        List headData12 = (List) model.get("useOurOther");
        int sumOur = 0;
        int sumOther = 0;
        for (int j4 = 0; j4 < headData12.size(); j4++) {
            RecordDto record2 = (RecordDto) headData12.get(j4);
            headrow13.createCell(j4 + 3).setCellValue(record2.getInt("CNT"));
            if (record2.getString("REGION_ID").equals("000")) {
                sumOther += record2.getInt("CNT");
            } else {
                sumOur += record2.getInt("CNT");
            }
        }
        headrow13.createCell(1).setCellValue(sumOur);
        headrow13.createCell(2).setCellValue(sumOther);
        int rowCnt6 = rowCnt5 + 1;
        Calendar ca = Calendar.getInstance();
        int thYear = Integer.parseInt(thDateTime.substring(0, 4));
        int thMonth = Integer.parseInt(thDateTime.substring(4, 6));
        ca.set(thYear, i, thMonth);
        int maxMon = ca.getActualMaximum(5);
        String date = (String) model.get("start_date");
        HSSFRow[] dataRow = new HSSFRow[maxMon];
        int[] sumOurArr = new int[maxMon];
        int[] sumOtherArr = new int[maxMon];
        for (int i2 = 0; i2 < maxMon; i2++) {
            dataRow[i2] = sheet1.createRow(rowCnt6 + i2);
            sumOurArr[i2] = 0;
            sumOtherArr[i2] = 0;
            if (i2 < 9) {
                dataRow[i2].createCell(0).setCellValue(date.concat("0").concat(Integer.toString(i2 + 1)));
            } else {
                dataRow[i2].createCell(0).setCellValue(date.concat(Integer.toString(i2 + 1)));
            }
        }
        List data = (List) model.get("useDaily");
        for (int i3 = 0; i3 < data.size(); i3++) {
            RecordDto record3 = (RecordDto) data.get(i3);
            int statDate = Integer.parseInt(record3.getString("STAT_DATE").substring(6, 8));
            String regionId = record3.getString("REGION_ID");
            String informerType = record3.getString("INFORMER_TYPE");
            int j5 = 0;
            while (true) {
                if (j5 >= headData.size()) {
                    break;
                }
                RecordDto recordType = (RecordDto) headData.get(j5);
                if (!informerType.equals(recordType.getString("CODE"))) {
                    j5++;
                } else if (regionId.equals("000")) {
                    dataRow[statDate - 1].createCell((j5 * 2) + 4).setCellValue(record3.getInt("CNT"));
                    sumOtherArr[statDate - 1] = sumOtherArr[statDate - 1] + record3.getInt("CNT");
                } else {
                    dataRow[statDate - 1].createCell((j5 * 2) + 3).setCellValue(record3.getInt("CNT"));
                    sumOurArr[statDate - 1] = sumOurArr[statDate - 1] + record3.getInt("CNT");
                }
            }
        }
        for (int i4 = 0; i4 < maxMon; i4++) {
            dataRow[i4].createCell(1).setCellValue(sumOurArr[i4]);
            dataRow[i4].createCell(2).setCellValue(sumOtherArr[i4]);
        }
        HSSFSheet sheet2 = wb.createSheet(model.get("sheetNames2").toString());
        HSSFRow titlerow2 = sheet2.createRow(0);
        titlerow2.createCell(0).setCellValue(model.get("sheetNames2").toString());
        int rowCnt7 = 0 + 1;
        HSSFRow headrow2 = sheet2.createRow(rowCnt7);
        headrow2.createCell(0).setCellValue("구분");
        headrow2.createCell(1).setCellValue("계");
        List headData2 = (List) model.get("regionHead");
        for (int j6 = 0; j6 < headData2.size(); j6++) {
            headrow2.createCell((j6 * 4) + 5).setCellValue(((RecordDto) headData2.get(j6)).getString("CODE_NAME"));
        }
        for (int i5 = 0; i5 < headData2.size() + 1; i5++) {
            sheet2.addMergedRegion(new CellRangeAddress(rowCnt7, rowCnt7, (i5 * 4) + 1, (i5 * 4) + 4));
        }
        int rowCnt8 = rowCnt7 + 1;
        HSSFRow headrow14 = sheet2.createRow(rowCnt8);
        List headData13 = (List) model.get("sumMonth");
        int sum2 = 0;
        int sumMid = 0;
        List<Integer> sumMidList = new ArrayList<>();
        for (int j7 = 0; j7 < headData13.size(); j7++) {
            RecordDto record4 = (RecordDto) headData13.get(j7);
            if (j7 % 4 == 0 && j7 > 0) {
                sumMidList.add(Integer.valueOf(sumMid));
                sumMid = 0 + record4.getInt("CNT");
            } else if (j7 + 1 == headData13.size()) {
                sumMid += record4.getInt("CNT");
                sumMidList.add(Integer.valueOf(sumMid));
            } else {
                sumMid += record4.getInt("CNT");
            }
            sum2 += record4.getInt("CNT");
        }
        headrow14.createCell(1).setCellValue(sum2);
        for (int k = 0; k < sumMidList.size(); k++) {
            headrow14.createCell((k * 4) + 5).setCellValue(sumMidList.get(k).intValue());
        }
        for (int i6 = 0; i6 < headData2.size() + 1; i6++) {
            sheet2.addMergedRegion(new CellRangeAddress(rowCnt8, rowCnt8, (i6 * 4) + 1, (i6 * 4) + 4));
        }
        int rowCnt9 = rowCnt8 + 1;
        HSSFRow headrow15 = sheet2.createRow(rowCnt9);
        for (int j8 = 0; j8 < headData2.size() + 1; j8++) {
            headrow15.createCell((j8 * 4) + 1).setCellValue("방송자료");
            headrow15.createCell((j8 * 4) + 2).setCellValue("자체처리");
            headrow15.createCell((j8 * 4) + 3).setCellValue("안내");
            headrow15.createCell((j8 * 4) + 4).setCellValue("기관통보");
        }
        int rowCnt10 = rowCnt9 + 1;
        HSSFRow headrow16 = sheet2.createRow(rowCnt10);
        List headData14 = (List) model.get("sumMonth");
        int sumSendY = 0;
        int sumSendN = 0;
        int sumA07 = 0;
        int sumA08 = 0;
        for (int i7 = 0; i7 < headData14.size(); i7++) {
            RecordDto record5 = (RecordDto) headData14.get(i7);
            String regionId2 = record5.getString("CODE");
            String titleStr = record5.getString("SEQ");
            for (int j9 = 0; j9 < headData2.size(); j9++) {
                RecordDto recordType2 = (RecordDto) headData2.get(j9);
                if (recordType2.getString("CODE").equals(regionId2)) {
                    if (titleStr.equals("B")) {
                        headrow16.createCell((j9 * 4) + 5).setCellValue(record5.getInt("CNT"));
                        sumSendY += record5.getInt("CNT");
                    } else if (titleStr.equals("NB")) {
                        headrow16.createCell((j9 * 4) + 6).setCellValue(record5.getInt("CNT"));
                        sumSendN += record5.getInt("CNT");
                    } else if (titleStr.equals("A07")) {
                        headrow16.createCell((j9 * 4) + 7).setCellValue(record5.getInt("CNT"));
                        sumA07 += record5.getInt("CNT");
                    } else if (titleStr.equals("A08")) {
                        headrow16.createCell((j9 * 4) + 8).setCellValue(record5.getInt("CNT"));
                        sumA08 += record5.getInt("CNT");
                    }
                }
            }
        }
        headrow16.createCell(1).setCellValue(sumSendY);
        headrow16.createCell(2).setCellValue(sumSendN);
        headrow16.createCell(3).setCellValue(sumA07);
        headrow16.createCell(4).setCellValue(sumA08);
        int rowCnt11 = rowCnt10 + 1;
        HSSFRow[] dataRow1 = new HSSFRow[maxMon];
        int[] sumSendYArr = new int[maxMon];
        int[] sumSendNArr = new int[maxMon];
        int[] sumA07Arr = new int[maxMon];
        int[] sumA08Arr = new int[maxMon];
        for (int i8 = 0; i8 < maxMon; i8++) {
            dataRow1[i8] = sheet2.createRow(rowCnt11 + i8);
            sumSendYArr[i8] = 0;
            sumSendNArr[i8] = 0;
            sumA07Arr[i8] = 0;
            sumA08Arr[i8] = 0;
            if (i8 < 9) {
                dataRow1[i8].createCell(0).setCellValue(date.concat("0").concat(Integer.toString(i8 + 1)));
            } else {
                dataRow1[i8].createCell(0).setCellValue(date.concat(Integer.toString(i8 + 1)));
            }
        }
        List data2 = (List) model.get("Data");
        for (int k2 = 0; k2 < dataRow1.length; k2++) {
            for (int j10 = 0; j10 < headData2.size(); j10++) {
                RecordDto recordType3 = (RecordDto) headData2.get(j10);
                for (int i9 = 0; i9 < data2.size(); i9++) {
                    RecordDto record6 = (RecordDto) data2.get(i9);
                    int statDate2 = Integer.parseInt(record6.getString("STAT_DATE").substring(6, 8));
                    String regionId3 = record6.getString("INFORMER_TYPE");
                    String titleStr2 = record6.getString("SEQ");
                    if (statDate2 - 1 == k2) {
                        if (regionId3.equals(recordType3.getString("CODE"))) {
                            if (titleStr2.equals("B")) {
                                dataRow1[statDate2 - 1].createCell((j10 * 4) + 5).setCellValue(record6.getInt("CNT"));
                                sumSendYArr[statDate2 - 1] = sumSendYArr[statDate2 - 1] + record6.getInt("CNT");
                            } else if (titleStr2.equals("NB")) {
                                dataRow1[statDate2 - 1].createCell((j10 * 4) + 6).setCellValue(record6.getInt("CNT"));
                                sumSendNArr[statDate2 - 1] = sumSendNArr[statDate2 - 1] + record6.getInt("CNT");
                            } else if (titleStr2.equals("A07")) {
                                dataRow1[statDate2 - 1].createCell((j10 * 4) + 7).setCellValue(record6.getInt("CNT"));
                                sumA07Arr[statDate2 - 1] = sumA07Arr[statDate2 - 1] + record6.getInt("CNT");
                            } else if (titleStr2.equals("A08")) {
                                dataRow1[statDate2 - 1].createCell((j10 * 4) + 8).setCellValue(record6.getInt("CNT"));
                                sumA08Arr[statDate2 - 1] = sumA08Arr[statDate2 - 1] + record6.getInt("CNT");
                            }
                        }
                    } else {
                        if (dataRow1[k2].getCell((j10 * 4) + 5) == null) {
                            dataRow1[k2].createCell((j10 * 4) + 5).setCellValue(0.0d);
                            sumSendYArr[k2] = sumSendYArr[k2] + 0;
                        }
                        if (dataRow1[k2].getCell((j10 * 4) + 6) == null) {
                            dataRow1[k2].createCell((j10 * 4) + 6).setCellValue(0.0d);
                            sumSendYArr[k2] = sumSendYArr[k2] + 0;
                        }
                        if (dataRow1[k2].getCell((j10 * 4) + 7) == null) {
                            dataRow1[k2].createCell((j10 * 4) + 7).setCellValue(0.0d);
                            sumSendYArr[k2] = sumSendYArr[k2] + 0;
                        }
                        if (dataRow1[k2].getCell((j10 * 4) + 8) == null) {
                            dataRow1[k2].createCell((j10 * 4) + 8).setCellValue(0.0d);
                            sumSendYArr[k2] = sumSendYArr[k2] + 0;
                        }
                    }
                }
            }
        }
        for (int i10 = 0; i10 < maxMon; i10++) {
            dataRow1[i10].createCell(1).setCellValue(sumSendYArr[i10]);
            dataRow1[i10].createCell(2).setCellValue(sumSendNArr[i10]);
            dataRow1[i10].createCell(3).setCellValue(sumA07Arr[i10]);
            dataRow1[i10].createCell(4).setCellValue(sumA08Arr[i10]);
        }
    }

    protected void nationalIncident(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        int rowCnt = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt);
        headrow.createCell(1).setCellValue("합계");
        headrow.createCell(2).setCellValue("공사");
        headrow.createCell(3).setCellValue("행사");
        headrow.createCell(4).setCellValue("사고");
        headrow.createCell(5).setCellValue("고장");
        headrow.createCell(6).setCellValue("기타");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        headrow2.createCell(0).setCellValue("계");
        int sumsc = 0;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow = new HSSFRow[12];
        String thDateTime = model.get("start_date").toString();
        int[] maxDay = new int[12];
        for (int i = 0; i < 12; i++) {
            dataRow[i] = sheet1.createRow(rowCnt3);
            dataRow[i].createCell(0).setCellValue(String.valueOf(i + 1) + "월");
            Calendar ca = Calendar.getInstance();
            int thYear = Integer.parseInt(thDateTime.substring(0, 4));
            int thMonth = Integer.parseInt(thDateTime.substring(4, 6));
            ca.set(thYear, i, thMonth);
            maxDay[i] = ca.getActualMaximum(5);
            rowCnt3++;
        }
        new DecimalFormat("##0.0");
        for (int i2 = 0; i2 < dataList.size(); i2++) {
            RecordDto record = (RecordDto) dataList.get(i2);
            int statDate = Integer.parseInt(record.getString("STAT_MONTH"));
            for (int j = 0; j < 12; j++) {
                if (j == statDate - 1) {
                    dataRow[statDate - 1].createCell(1).setCellValue(record.getInt("SUMC"));
                    dataRow[statDate - 1].createCell(2).setCellValue(record.getInt("CNT1"));
                    dataRow[statDate - 1].createCell(3).setCellValue(record.getInt("CNT2"));
                    dataRow[statDate - 1].createCell(4).setCellValue(record.getInt("CNT3"));
                    dataRow[statDate - 1].createCell(5).setCellValue(record.getInt("CNT4"));
                    dataRow[statDate - 1].createCell(6).setCellValue(record.getInt("CNT5"));
                    sumsc += record.getInt("SUMC");
                    sum1 += record.getInt("CNT1");
                    sum2 += record.getInt("CNT2");
                    sum3 += record.getInt("CNT3");
                    sum4 += record.getInt("CNT4");
                    sum5 += record.getInt("CNT5");
                }
            }
        }
        headrow2.createCell(1).setCellValue(sumsc);
        headrow2.createCell(2).setCellValue(sum1);
        headrow2.createCell(3).setCellValue(sum2);
        headrow2.createCell(4).setCellValue(sum3);
        headrow2.createCell(5).setCellValue(sum4);
        headrow2.createCell(6).setCellValue(sum5);
    }

    protected void standardInformer(Map model, HSSFWorkbook wb) {
        int rowCnt;
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt2 = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt2);
        headrow.createCell(0).setCellValue(model.get("start_date").toString());
        sheet1.addMergedRegion(new CellRangeAddress(rowCnt2, rowCnt2, 0, 12));
        int cellCnt = 4;
        int nwRowCnt = rowCnt2 + 1;
        int addCellCnt = 0;
        int bfMonSum = 0;
        int thMonSum = 0;
        int addSubSum = 0;
        int wiSum = 0;
        int haeSum = 0;
        for (int k = 0; k < dataList.size(); k++) {
            int rowCnt3 = nwRowCnt;
            RecordDto record = (RecordDto) dataList.get(k);
            String nwType = record.getString("IND_NAME");
            if (k > 0) {
                RecordDto rcd = (RecordDto) dataList.get(k - 1);
                String bfType = rcd.getString("IND_NAME");
                if (!nwType.equals(bfType)) {
                    int rowCnt4 = rowCnt3 + 1;
                    sheet1.getRow(rowCnt4).createCell(2).setCellValue(bfMonSum);
                    int rowCnt5 = rowCnt4 + 1;
                    sheet1.getRow(rowCnt5).createCell(2).setCellValue(thMonSum);
                    int rowCnt6 = rowCnt5 + 1;
                    sheet1.getRow(rowCnt6).createCell(2).setCellValue(addSubSum);
                    int rowCnt7 = rowCnt6 + 1;
                    sheet1.getRow(rowCnt7).createCell(3).setCellValue(wiSum);
                    sheet1.getRow(rowCnt7 + 1).createCell(3).setCellValue(haeSum);
                    bfMonSum = 0;
                    thMonSum = 0;
                    addSubSum = 0;
                    wiSum = 0;
                    haeSum = 0;
                    int rowCnt8 = nwRowCnt + 7;
                    cellCnt = 4;
                    addCellCnt = 0;
                    sheet1.createRow(rowCnt8).createCell(0).setCellValue((record.getInt("IND_TYPE") + 1) + "." + nwType);
                    sheet1.addMergedRegion(new CellRangeAddress(rowCnt8, rowCnt8, 0, 1));
                    rowCnt = rowCnt8 + 1;
                    nwRowCnt = rowCnt;
                } else {
                    rowCnt = nwRowCnt;
                }
            } else {
                sheet1.createRow(rowCnt3).createCell(0).setCellValue((record.getInt("IND_TYPE") + 1) + "." + nwType);
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt3, rowCnt3, 0, 1));
                rowCnt = rowCnt3 + 1;
                nwRowCnt = rowCnt;
            }
            if (sheet1.getRow(rowCnt) == null) {
                sheet1.createRow(rowCnt).createCell(1).setCellValue("구분");
                sheet1.getRow(rowCnt).createCell(2).setCellValue("계");
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 2, 3));
                sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("ORG_NAME"));
                int rowCnt9 = rowCnt + 1;
                sheet1.createRow(rowCnt9).createCell(1).setCellValue("전월");
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt9, rowCnt9, 2, 3));
                sheet1.getRow(rowCnt9).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT1"));
                int rowCnt10 = rowCnt9 + 1;
                sheet1.createRow(rowCnt10).createCell(1).setCellValue("금월");
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt10, rowCnt10, 2, 3));
                sheet1.getRow(rowCnt10).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT2"));
                int rowCnt11 = rowCnt10 + 1;
                sheet1.createRow(rowCnt11).createCell(1).setCellValue("증감");
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt11, rowCnt11, 2, 3));
                sheet1.getRow(rowCnt11).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT3"));
                int rowCnt12 = rowCnt11 + 1;
                sheet1.createRow(rowCnt12).createCell(1).setCellValue("비고");
                sheet1.getRow(rowCnt12).createCell(2).setCellValue("위촉");
                sheet1.getRow(rowCnt12).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT4"));
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt12, rowCnt12 + 1, 1, 1));
                int rowCnt13 = rowCnt12 + 1;
                sheet1.createRow(rowCnt13).createCell(2).setCellValue("해촉");
                sheet1.getRow(rowCnt13).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT5"));
            } else {
                sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("ORG_NAME"));
                int rowCnt14 = rowCnt + 1;
                sheet1.getRow(rowCnt14).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT1"));
                int rowCnt15 = rowCnt14 + 1;
                sheet1.getRow(rowCnt15).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT2"));
                int rowCnt16 = rowCnt15 + 1;
                sheet1.getRow(rowCnt16).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT3"));
                int rowCnt17 = rowCnt16 + 1;
                sheet1.getRow(rowCnt17).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT4"));
                sheet1.getRow(rowCnt17 + 1).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT5"));
            }
            bfMonSum += record.getInt("CNT1");
            thMonSum += record.getInt("CNT2");
            addSubSum += record.getInt("CNT3");
            wiSum += record.getInt("CNT4");
            haeSum += record.getInt("CNT5");
            addCellCnt++;
            if (k == dataList.size() - 1) {
                int rowCnt18 = nwRowCnt + 1;
                sheet1.getRow(rowCnt18).createCell(2).setCellValue(bfMonSum);
                int rowCnt19 = rowCnt18 + 1;
                sheet1.getRow(rowCnt19).createCell(2).setCellValue(thMonSum);
                int rowCnt20 = rowCnt19 + 1;
                sheet1.getRow(rowCnt20).createCell(2).setCellValue(addSubSum);
                int rowCnt21 = rowCnt20 + 1;
                sheet1.getRow(rowCnt21).createCell(3).setCellValue(wiSum);
                sheet1.getRow(rowCnt21 + 1).createCell(3).setCellValue(haeSum);
            }
        }
    }

    protected void receiptDown(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        sheet1.createRow(rowCnt);
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt2);
        headrow1.createCell(0).setCellValue("번호");
        headrow1.createCell(1).setCellValue("일자");
        headrow1.createCell(2).setCellValue("전송");
        headrow1.createCell(3).setCellValue("방송");
        headrow1.createCell(4).setCellValue("제보시간");
        headrow1.createCell(5).setCellValue("방송시간");
        headrow1.createCell(6).setCellValue("유형(대)");
        headrow1.createCell(7).setCellValue("유형(중)");
        headrow1.createCell(8).setCellValue("제보자");
        headrow1.createCell(9).setCellValue("구분");
        headrow1.createCell(10).setCellValue("접수자");
        headrow1.createCell(11).setCellValue("내용");
        headrow1.createCell(12).setCellValue("제보처");
        headrow1.createCell(13).setCellValue("교통방송");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow = new HSSFRow[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            RecordDto record = (RecordDto) dataList.get(i);
            dataRow[i].createCell(0).setCellValue(record.getString("RNUM"));
            dataRow[i].createCell(1).setCellValue(record.getString("RECEIPT_DAY"));
            dataRow[i].createCell(2).setCellValue(record.getString("FLAG_SEND"));
            dataRow[i].createCell(3).setCellValue(record.getString("FLAG_BROD"));
            dataRow[i].createCell(4).setCellValue(record.getString("RECEIPT_TIME"));
            dataRow[i].createCell(5).setCellValue(record.getString("BROAD_TIME"));
            dataRow[i].createCell(6).setCellValue(record.getString("REPORT_TYPE"));
            dataRow[i].createCell(7).setCellValue(record.getString("REPORT_TYPE2"));
            dataRow[i].createCell(8).setCellValue(record.getString("INDIVIDUAL_NAME"));
            dataRow[i].createCell(9).setCellValue(record.getString("TYPE_NAME"));
            dataRow[i].createCell(10).setCellValue(record.getString("RECEPTION_NAME"));
            dataRow[i].createCell(11).setCellValue(record.getString("MEMO"));
            dataRow[i].createCell(12).setCellValue(record.getString("REPORTER_TYPE"));
            dataRow[i].createCell(13).setCellValue(record.getString("REGION_NAME"));
        }
    }

    protected void dayReceipt(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("dayReceiptList");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        HSSFRow headrow0 = sheet1.createRow(rowCnt);
        headrow0.createCell(0).setCellValue("건수 : ");
        headrow0.createCell(1).setCellValue(dataList.size());
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt2);
        headrow1.createCell(0).setCellValue("연번");
        headrow1.createCell(1).setCellValue("ID");
        headrow1.createCell(2).setCellValue("성명");
        headrow1.createCell(3).setCellValue("전화번호");
        headrow1.createCell(4).setCellValue("기관명");
        headrow1.createCell(5).setCellValue("생일");
        headrow1.createCell(6).setCellValue("월계");
        headrow1.createCell(7).setCellValue("1");
        headrow1.createCell(8).setCellValue("2");
        headrow1.createCell(9).setCellValue("3");
        headrow1.createCell(10).setCellValue("4");
        headrow1.createCell(11).setCellValue("5");
        headrow1.createCell(12).setCellValue("6");
        headrow1.createCell(13).setCellValue("7");
        headrow1.createCell(14).setCellValue("8");
        headrow1.createCell(15).setCellValue("9");
        headrow1.createCell(16).setCellValue("10");
        headrow1.createCell(17).setCellValue("11");
        headrow1.createCell(18).setCellValue("12");
        headrow1.createCell(19).setCellValue("13");
        headrow1.createCell(20).setCellValue("14");
        headrow1.createCell(21).setCellValue("15");
        headrow1.createCell(22).setCellValue("16");
        headrow1.createCell(23).setCellValue("17");
        headrow1.createCell(24).setCellValue("18");
        headrow1.createCell(25).setCellValue("19");
        headrow1.createCell(26).setCellValue("20");
        headrow1.createCell(27).setCellValue("21");
        headrow1.createCell(28).setCellValue("22");
        headrow1.createCell(29).setCellValue("23");
        headrow1.createCell(30).setCellValue("24");
        headrow1.createCell(31).setCellValue("25");
        headrow1.createCell(32).setCellValue("26");
        headrow1.createCell(33).setCellValue("27");
        headrow1.createCell(34).setCellValue("28");
        headrow1.createCell(35).setCellValue("29");
        headrow1.createCell(36).setCellValue("30");
        headrow1.createCell(37).setCellValue("31");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow = new HSSFRow[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            RecordDto record = (RecordDto) dataList.get(i);
            dataRow[i].createCell(0).setCellValue(i + 1);
            dataRow[i].createCell(1).setCellValue(record.getString("ACT_ID"));
            dataRow[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
            dataRow[i].createCell(3).setCellValue(record.getString("PHONE_CELL"));
            dataRow[i].createCell(4).setCellValue(record.getString("ORG_NAME"));
            dataRow[i].createCell(5).setCellValue(record.getString("BIRTHDAY"));
            dataRow[i].createCell(6).setCellValue(record.getString("SUM_CNT"));
            dataRow[i].createCell(7).setCellValue(record.getString("D1"));
            dataRow[i].createCell(8).setCellValue(record.getString("D2"));
            dataRow[i].createCell(9).setCellValue(record.getString("D3"));
            dataRow[i].createCell(10).setCellValue(record.getString("D4"));
            dataRow[i].createCell(11).setCellValue(record.getString("D5"));
            dataRow[i].createCell(12).setCellValue(record.getString("D6"));
            dataRow[i].createCell(13).setCellValue(record.getString("D7"));
            dataRow[i].createCell(14).setCellValue(record.getString("D8"));
            dataRow[i].createCell(15).setCellValue(record.getString("D9"));
            dataRow[i].createCell(16).setCellValue(record.getString("D10"));
            dataRow[i].createCell(17).setCellValue(record.getString("D11"));
            dataRow[i].createCell(18).setCellValue(record.getString("D12"));
            dataRow[i].createCell(19).setCellValue(record.getString("D13"));
            dataRow[i].createCell(20).setCellValue(record.getString("D14"));
            dataRow[i].createCell(21).setCellValue(record.getString("D15"));
            dataRow[i].createCell(22).setCellValue(record.getString("D16"));
            dataRow[i].createCell(23).setCellValue(record.getString("D17"));
            dataRow[i].createCell(24).setCellValue(record.getString("D18"));
            dataRow[i].createCell(25).setCellValue(record.getString("D19"));
            dataRow[i].createCell(26).setCellValue(record.getString("D20"));
            dataRow[i].createCell(27).setCellValue(record.getString("D21"));
            dataRow[i].createCell(28).setCellValue(record.getString("D22"));
            dataRow[i].createCell(29).setCellValue(record.getString("D23"));
            dataRow[i].createCell(30).setCellValue(record.getString("D24"));
            dataRow[i].createCell(31).setCellValue(record.getString("D25"));
            dataRow[i].createCell(32).setCellValue(record.getString("D26"));
            dataRow[i].createCell(33).setCellValue(record.getString("D27"));
            dataRow[i].createCell(34).setCellValue(record.getString("D28"));
            dataRow[i].createCell(35).setCellValue(record.getString("D29"));
            dataRow[i].createCell(36).setCellValue(record.getString("D30"));
            dataRow[i].createCell(37).setCellValue(record.getString("D31"));
        }
    }

    protected void volunteer(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("vltList");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        HSSFRow headrow0 = sheet1.createRow(rowCnt);
        headrow0.createCell(0).setCellValue("건수 : ");
        headrow0.createCell(1).setCellValue(dataList.size());
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt2);
        headrow1.createCell(0).setCellValue("연번");
        headrow1.createCell(1).setCellValue("ID");
        headrow1.createCell(2).setCellValue("성명");
        headrow1.createCell(3).setCellValue("전화번호");
        headrow1.createCell(4).setCellValue("기관명");
        headrow1.createCell(5).setCellValue("생일");
        headrow1.createCell(6).setCellValue("월계");
        headrow1.createCell(7).setCellValue("1");
        headrow1.createCell(8).setCellValue("2");
        headrow1.createCell(9).setCellValue("3");
        headrow1.createCell(10).setCellValue("4");
        headrow1.createCell(11).setCellValue("5");
        headrow1.createCell(12).setCellValue("6");
        headrow1.createCell(13).setCellValue("7");
        headrow1.createCell(14).setCellValue("8");
        headrow1.createCell(15).setCellValue("9");
        headrow1.createCell(16).setCellValue("10");
        headrow1.createCell(17).setCellValue("11");
        headrow1.createCell(18).setCellValue("12");
        headrow1.createCell(19).setCellValue("13");
        headrow1.createCell(20).setCellValue("14");
        headrow1.createCell(21).setCellValue("15");
        headrow1.createCell(22).setCellValue("16");
        headrow1.createCell(23).setCellValue("17");
        headrow1.createCell(24).setCellValue("18");
        headrow1.createCell(25).setCellValue("19");
        headrow1.createCell(26).setCellValue("20");
        headrow1.createCell(27).setCellValue("21");
        headrow1.createCell(28).setCellValue("22");
        headrow1.createCell(29).setCellValue("23");
        headrow1.createCell(30).setCellValue("24");
        headrow1.createCell(31).setCellValue("25");
        headrow1.createCell(32).setCellValue("26");
        headrow1.createCell(33).setCellValue("27");
        headrow1.createCell(34).setCellValue("28");
        headrow1.createCell(35).setCellValue("29");
        headrow1.createCell(36).setCellValue("30");
        headrow1.createCell(37).setCellValue("31");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow = new HSSFRow[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            RecordDto record = (RecordDto) dataList.get(i);
            dataRow[i].createCell(0).setCellValue(i + 1);
            dataRow[i].createCell(1).setCellValue(record.getString("ACT_ID"));
            dataRow[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
            dataRow[i].createCell(3).setCellValue(record.getString("PHONE_CELL"));
            dataRow[i].createCell(4).setCellValue(record.getString("ORG_NAME"));
            dataRow[i].createCell(5).setCellValue(record.getString("BIRTHDAY"));
            dataRow[i].createCell(6).setCellValue(record.getString("SUM_CNT"));
            dataRow[i].createCell(7).setCellValue(record.getString("D1"));
            dataRow[i].createCell(8).setCellValue(record.getString("D2"));
            dataRow[i].createCell(9).setCellValue(record.getString("D3"));
            dataRow[i].createCell(10).setCellValue(record.getString("D4"));
            dataRow[i].createCell(11).setCellValue(record.getString("D5"));
            dataRow[i].createCell(12).setCellValue(record.getString("D6"));
            dataRow[i].createCell(13).setCellValue(record.getString("D7"));
            dataRow[i].createCell(14).setCellValue(record.getString("D8"));
            dataRow[i].createCell(15).setCellValue(record.getString("D9"));
            dataRow[i].createCell(16).setCellValue(record.getString("D10"));
            dataRow[i].createCell(17).setCellValue(record.getString("D11"));
            dataRow[i].createCell(18).setCellValue(record.getString("D12"));
            dataRow[i].createCell(19).setCellValue(record.getString("D13"));
            dataRow[i].createCell(20).setCellValue(record.getString("D14"));
            dataRow[i].createCell(21).setCellValue(record.getString("D15"));
            dataRow[i].createCell(22).setCellValue(record.getString("D16"));
            dataRow[i].createCell(23).setCellValue(record.getString("D17"));
            dataRow[i].createCell(24).setCellValue(record.getString("D18"));
            dataRow[i].createCell(25).setCellValue(record.getString("D19"));
            dataRow[i].createCell(26).setCellValue(record.getString("D20"));
            dataRow[i].createCell(27).setCellValue(record.getString("D21"));
            dataRow[i].createCell(28).setCellValue(record.getString("D22"));
            dataRow[i].createCell(29).setCellValue(record.getString("D23"));
            dataRow[i].createCell(30).setCellValue(record.getString("D24"));
            dataRow[i].createCell(31).setCellValue(record.getString("D25"));
            dataRow[i].createCell(32).setCellValue(record.getString("D26"));
            dataRow[i].createCell(33).setCellValue(record.getString("D27"));
            dataRow[i].createCell(34).setCellValue(record.getString("D28"));
            dataRow[i].createCell(35).setCellValue(record.getString("D29"));
            dataRow[i].createCell(36).setCellValue(record.getString("D30"));
            dataRow[i].createCell(37).setCellValue(record.getString("D31"));
        }
    }

    protected void korLx(Map model, HSSFWorkbook wb) {
        int gl;
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        HSSFRow headrow = sheet1.createRow(rowCnt);
        headrow.createCell(0).setCellValue("실적날짜");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow2 = sheet1.createRow(rowCnt2);
        if (model.get("sheetNames1").toString().equals("가스기술공사 제보실적")) {
            headrow2.createCell(0).setCellValue("연번");
            headrow2.createCell(1).setCellValue("ID");
            headrow2.createCell(2).setCellValue("이 름");
            headrow2.createCell(3).setCellValue("제공건수");
            gl = 3;
        } else {
            headrow2.createCell(0).setCellValue("순번");
            headrow2.createCell(1).setCellValue("ID");
            headrow2.createCell(2).setCellValue("성 명");
            headrow2.createCell(3).setCellValue("전화번호");
            headrow2.createCell(4).setCellValue("제공건수");
            gl = 4;
        }
        headrow2.createCell(gl + 1).setCellValue("1");
        headrow2.createCell(gl + 2).setCellValue("2");
        headrow2.createCell(gl + 3).setCellValue("3");
        headrow2.createCell(gl + 4).setCellValue("4");
        headrow2.createCell(gl + 5).setCellValue("5");
        headrow2.createCell(gl + 6).setCellValue("6");
        headrow2.createCell(gl + 7).setCellValue("7");
        headrow2.createCell(gl + 8).setCellValue("8");
        headrow2.createCell(gl + 9).setCellValue("9");
        headrow2.createCell(gl + 10).setCellValue("10");
        headrow2.createCell(gl + 11).setCellValue("11");
        headrow2.createCell(gl + 12).setCellValue("12");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow1 = new HSSFRow[dataList.size()];
        for (int i = 0; i < dataRow1.length; i++) {
            RecordDto record = (RecordDto) dataList.get(i);
            dataRow1[i] = sheet1.createRow(rowCnt3);
            if (!record.getString("ACT_ID").equals("sum")) {
                if (model.get("sheetNames1").toString().equals("가스기술공사 제보실적")) {
                    dataRow1[i].createCell(0).setCellValue(i + 1);
                    dataRow1[i].createCell(1).setCellValue(record.getString("ACT_ID"));
                    dataRow1[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
                    dataRow1[i].createCell(3).setCellValue(record.getString("SUM_CNT"));
                } else {
                    dataRow1[i].createCell(0).setCellValue(i + 1);
                    dataRow1[i].createCell(1).setCellValue(record.getString("ACT_ID"));
                    dataRow1[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
                    dataRow1[i].createCell(3).setCellValue(record.getString("PHONE_CELL"));
                    dataRow1[i].createCell(4).setCellValue(record.getString("SUM_CNT"));
                }
            } else {
                dataRow1[i].createCell(0).setCellValue("합      계");
                sheet1.addMergedRegion(new CellRangeAddress(rowCnt3, rowCnt3, 0, gl - 1));
                dataRow1[i].createCell(gl).setCellValue(record.getString("SUM_CNT"));
            }
            dataRow1[i].createCell(gl + 1).setCellValue(record.getString("D1"));
            dataRow1[i].createCell(gl + 2).setCellValue(record.getString("D2"));
            dataRow1[i].createCell(gl + 3).setCellValue(record.getString("D3"));
            dataRow1[i].createCell(gl + 4).setCellValue(record.getString("D4"));
            dataRow1[i].createCell(gl + 5).setCellValue(record.getString("D5"));
            dataRow1[i].createCell(gl + 6).setCellValue(record.getString("D6"));
            dataRow1[i].createCell(gl + 7).setCellValue(record.getString("D7"));
            dataRow1[i].createCell(gl + 8).setCellValue(record.getString("D8"));
            dataRow1[i].createCell(gl + 9).setCellValue(record.getString("D9"));
            dataRow1[i].createCell(gl + 10).setCellValue(record.getString("D10"));
            dataRow1[i].createCell(gl + 11).setCellValue(record.getString("D11"));
            dataRow1[i].createCell(gl + 12).setCellValue(record.getString("D12"));
            rowCnt3++;
        }
    }

    protected void informerDown(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("Data");
        HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
        HSSFRow titlerow = sheet1.createRow(0);
        titlerow.createCell(0).setCellValue(model.get("titleName").toString());
        int rowCnt = 0 + 1;
        HSSFRow headrow0 = sheet1.createRow(rowCnt);
        headrow0.createCell(0).setCellValue("합계 : " + dataList.size() + "건");
        int rowCnt2 = rowCnt + 1;
        HSSFRow headrow1 = sheet1.createRow(rowCnt2);
        headrow1.createCell(0).setCellValue("ID");
        headrow1.createCell(1).setCellValue("방송국");
        headrow1.createCell(2).setCellValue("유형");
        headrow1.createCell(3).setCellValue("소속기관");
        headrow1.createCell(4).setCellValue("이름");
        headrow1.createCell(5).setCellValue("전화");
        headrow1.createCell(6).setCellValue("활동여부");
        headrow1.createCell(7).setCellValue("등록일");
        int rowCnt3 = rowCnt2 + 1;
        HSSFRow[] dataRow = new HSSFRow[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataRow[i] = sheet1.createRow(rowCnt3 + i);
            InfrmVO record = (InfrmVO) dataList.get(i);
            dataRow[i].createCell(0).setCellValue(record.getActId());
            dataRow[i].createCell(1).setCellValue(record.getAreaName());
            dataRow[i].createCell(2).setCellValue(record.getInformerTypeName());
            dataRow[i].createCell(3).setCellValue(record.getOrgName());
            dataRow[i].createCell(4).setCellValue(record.getInformerName());
            dataRow[i].createCell(5).setCellValue(record.getPhoneCell());
            if (record.getFlagAct().equals("Y")) {
                dataRow[i].createCell(6).setCellValue("위촉");
            } else {
                dataRow[i].createCell(6).setCellValue("해촉");
            }
            dataRow[i].createCell(7).setCellValue(record.getRegDate());
        }
    }
}