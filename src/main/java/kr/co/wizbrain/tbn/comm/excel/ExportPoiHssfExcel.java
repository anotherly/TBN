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
            } else if (model.get("mapping").equals("orgOrgSub")) {
            	orgOrgSub(model, wb);
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

	protected void informerType(HSSFWorkbook wb, String titleData, List headData, List headData1, List headData2,
			List data) {
		int rowCnt = 0;
		HSSFSheet sheet1 = wb.createSheet(titleData);
		String csType = "";
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue("교통정보 제공대장");
		titlerow.setHeight((short) 800);
		rowCnt++;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		headrow.createCell(0).setCellValue(titleData);
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		headrow.createCell(2).setCellValue("계");
		
		for (int j = 0; j < headData.size(); ++j) {
			RecordDto record = (RecordDto) headData.get(j);
			headrow.createCell(j * 2 + 4).setCellValue(record.getString("CODE_NAME"));
		}

		rowCnt++;
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		int sum = 0;

		for (int j = 0; j < headData1.size(); ++j) {
			RecordDto record = (RecordDto) headData1.get(j);
			headrow1.createCell(j * 2 + 4).setCellValue((double) record.getInt("CNT"));
			sum += record.getInt("CNT");
		}

		headrow1.createCell(0).setCellValue("수집건수");
		headrow1.createCell(2).setCellValue((double) sum);

		for (int j = 0; j < headData.size() + 2; ++j) {
			sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt - 1, j * 2, j * 2 + 1));
			if (j == 0) {
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt + 2, j * 2, j * 2 + 1));
			} else {
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, j * 2, j * 2 + 1));
			}
		}

		rowCnt++;
		headrow1 = sheet1.createRow(rowCnt);

		for (int j = 0; j < headData.size() + 1; ++j) {
			headrow1.createCell(j * 2 + 2).setCellValue("자국");
			headrow1.createCell(j * 2 + 3).setCellValue("타국");
		}

		rowCnt++;
		headrow1 = sheet1.createRow(rowCnt);
		int sumOur = 0;
		int sumOther = 0;

		for (int j = 0; j < headData2.size(); ++j) {
			RecordDto record = (RecordDto) headData2.get(j);
			headrow1.createCell(j + 4).setCellValue((double) record.getInt("CNT"));
			if (record.getString("REGION_ID").equals("000")) {
				sumOther += record.getInt("CNT");
			} else {
				sumOur += record.getInt("CNT");
			}
		}

		headrow1.createCell(2).setCellValue((double) sumOur);
		headrow1.createCell(3).setCellValue((double) sumOther);
		rowCnt++;
		DecimalFormat df = new DecimalFormat("##0.0%");

		for (int i = 0; i < data.size() / (headData.size() * 2); ++i) {
			HSSFRow dataRow = sheet1.createRow(rowCnt++);
			HSSFRow ratioRow = sheet1.createRow(rowCnt++);
			sumOur = 0;
			sumOther = 0;

			for (int j = 0; j < headData.size() * 2; ++j) {
				RecordDto record = (RecordDto) data.get(i * headData.size() * 2 + j);
				if (j == 0) {
					String TYPE = record.getString("TYPE");
					if (!TYPE.contains("CASTER") && !TYPE.contains("STUDIO")) {
						if (!TYPE.equals("전송건수") && !TYPE.equals("방송건수")) {
							dataRow.createCell(0).setCellValue(TYPE);
							dataRow.createCell(1).setCellValue("건수");
							ratioRow.createCell(1).setCellValue("비율");
						} else {
							if (TYPE.equals("전송건수")) {
								dataRow.createCell(0).setCellValue("중요제보");
							} else {
								ratioRow.createCell(1).setCellValue("방송비율");
							}

							dataRow.createCell(1).setCellValue(TYPE);
						}
					} else {
						csType = TYPE.split("_")[0];
						TYPE = TYPE.split("_")[1];
						dataRow.createCell(1).setCellValue(TYPE);
					}
				}

				if (dataRow.getCell(0) != null && !dataRow.getCell(0).getStringCellValue().equals("방송")
						&& !dataRow.getCell(0).getStringCellValue().equals("비방송")) {
					dataRow.getCell(0).getStringCellValue().equals("중요제보");
				}

				dataRow.createCell(j + 4).setCellValue((double) record.getInt("CNT"));
				if (!dataRow.getCell(1).getStringCellValue().equals("STUDIO")
						&& !dataRow.getCell(1).getStringCellValue().equals("CASTER")
						&& !dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
					if (dataRow.getCell(0) != null) {
						if (!dataRow.getCell(0).getStringCellValue().equals("방송")
								&& !dataRow.getCell(0).getStringCellValue().equals("비방송")) {
							if (headrow1.getCell(j + 4).getNumericCellValue() == 0.0) {
								ratioRow.createCell(j + 4).setCellValue("0.0%");
							} else {
								ratioRow.createCell(j + 4).setCellValue(df.format(
										record.getDouble("CNT") / headrow1.getCell(j + 4).getNumericCellValue()));
							}
						} else if (headrow1.getCell(j + 4).getNumericCellValue() == 0.0) {
							ratioRow.createCell(j + 4).setCellValue("0.0%");
						} else {
							ratioRow.createCell(j + 4).setCellValue(df.format(
									record.getDouble("CNT") / sheet1.getRow(7).getCell(j + 4).getNumericCellValue()));
						}
					} else if (headrow1.getCell(j + 4).getNumericCellValue() == 0.0) {
						ratioRow.createCell(j + 4).setCellValue("0.0%");
					} else {
						ratioRow.createCell(j + 4).setCellValue(df.format(
								record.getDouble("CNT") / sheet1.getRow(15).getCell(j + 4).getNumericCellValue()));
					}
				}

				if (record.getString("REGION_ID").equals("000")) {
					sumOther += record.getInt("CNT");
				} else {
					sumOur += record.getInt("CNT");
				}
			}

			dataRow.createCell(2).setCellValue((double) sumOur);
			dataRow.createCell(3).setCellValue((double) sumOther);
			if (!dataRow.getCell(1).getStringCellValue().equals("STUDIO")
					&& !dataRow.getCell(1).getStringCellValue().equals("CASTER")
					&& !dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
				if (dataRow.getCell(0) != null) {
					if (!dataRow.getCell(0).getStringCellValue().equals("방송")
							&& !dataRow.getCell(0).getStringCellValue().equals("비방송")) {
						if (headrow1.getCell(2).getNumericCellValue() == 0.0) {
							ratioRow.createCell(2).setCellValue("0.0%");
						} else {
							ratioRow.createCell(2).setCellValue(
									df.format((double) sumOur / headrow1.getCell(2).getNumericCellValue()));
						}

						if (headrow1.getCell(3).getNumericCellValue() == 0.0) {
							ratioRow.createCell(3).setCellValue("0.0%");
						} else {
							ratioRow.createCell(3).setCellValue(
									df.format((double) sumOther / headrow1.getCell(3).getNumericCellValue()));
						}
					} else {
						if (headrow1.getCell(2).getNumericCellValue() == 0.0) {
							ratioRow.createCell(2).setCellValue("0.0%");
						} else {
							ratioRow.createCell(2).setCellValue(
									df.format((double) sumOur / sheet1.getRow(7).getCell(2).getNumericCellValue()));
						}

						if (headrow1.getCell(3).getNumericCellValue() == 0.0) {
							ratioRow.createCell(3).setCellValue("0.0%");
						} else {
							ratioRow.createCell(3).setCellValue(
									df.format((double) sumOther / sheet1.getRow(7).getCell(3).getNumericCellValue()));
						}
					}
				} else {
					if (headrow1.getCell(2).getNumericCellValue() == 0.0) {
						ratioRow.createCell(2).setCellValue("0.0%");
					} else {
						ratioRow.createCell(2).setCellValue(
								df.format((double) sumOur / sheet1.getRow(15).getCell(2).getNumericCellValue()));
					}

					if (headrow1.getCell(3).getNumericCellValue() == 0.0) {
						ratioRow.createCell(3).setCellValue("0.0%");
					} else {
						ratioRow.createCell(3).setCellValue(
								df.format((double) sumOther / sheet1.getRow(15).getCell(3).getNumericCellValue()));
					}
				}
			} else {
				rowCnt--;
			}

			log.debug("★★★ " + (rowCnt - 1));
			log.debug("★★★ " + dataRow.getCell(1).getStringCellValue());
			if (csType.equals("방송") && dataRow.getCell(1).getStringCellValue().equals("CASTER")) {
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 4, rowCnt - 1, 0, 0));
			} else if (csType.equals("중요제보") && dataRow.getCell(1).getStringCellValue().equals("CASTER")) {
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 5, rowCnt - 1, 0, 0));
			} else if (dataRow.getCell(0) != null && !dataRow.getCell(0).getStringCellValue().equals("방송")
					&& !dataRow.getCell(1).getStringCellValue().equals("STUDIO")
					&& !dataRow.getCell(1).getStringCellValue().equals("방송건수")
					&& !dataRow.getCell(1).getStringCellValue().equals("전송건수")) {
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 2, rowCnt - 1, 0, 0));
			}
		}

	}
    
    protected void extrBro(Map model, HSSFWorkbook wb) {
		int i = 0;
		int j = 0;
		int rowCnt = 0;
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		rowCnt++;
		rowCnt++;
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		headrow1.createCell(0).setCellValue("일자");
		headrow1.createCell(1).setCellValue("총 긴급정보 건수");
		headrow1.createCell(2).setCellValue("5분내 방송처리 건수");
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
		int sum1 = 0;
		int sum2 = 0;
		List data = (List) model.get("Data");
		rowCnt++;
		Calendar ca = Calendar.getInstance();
		String date = (String) model.get("start_date");
		int thYear = Integer.parseInt(date.substring(0, 4));
		int thMonth = Integer.parseInt(date.substring(4, 6));
		ca.set(thYear, i, thMonth);
		int maxMon = ca.getActualMaximum(5);
		HSSFRow[] dataRow = new HSSFRow[maxMon];
		int[] sumOurArr = new int[maxMon];
		int[] sumOtherArr = new int[maxMon];

		for (i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			String inputDate = "";
			if (i < 9) {
				inputDate = date.concat("0").concat(Integer.toString(i + 1));
			} else {
				inputDate = date.concat(Integer.toString(i + 1));
			}

			dataRow[i].createCell(0).setCellValue(inputDate);

			for (int k = 0; k < data.size(); ++k) {
				RecordDto record = (RecordDto) data.get(k);
				if (inputDate.equals(record.getString("KEY_DATE"))) {
					dataRow[i].createCell(1).setCellValue((double) record.getInt("CNT1"));
					dataRow[i].createCell(2).setCellValue((double) record.getInt("CNT2"));
					sum1 += record.getInt("CNT1");
					sum2 += record.getInt("CNT2");
					break;
				}

				dataRow[i].createCell(1).setCellValue(0.0);
				dataRow[i].createCell(2).setCellValue(0.0);
			}
		}

		headrow2.createCell(0).setCellValue("계");
		headrow2.createCell(1).setCellValue((double) sum1);
		headrow2.createCell(2).setCellValue((double) sum2);
    }

    protected void disastorStat(Map model, HSSFWorkbook wb) {
		int i = 0;
		int j = 0;
		int rowCnt = 0;
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		rowCnt++;
		rowCnt++;
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		headrow1.createCell(0).setCellValue("일자");
		headrow1.createCell(1).setCellValue("일별 총 제보건수");
		headrow1.createCell(2).setCellValue("재난제보 건수");
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
		int sum1 = 0;
		int sum2 = 0;
		List data = (List) model.get("Data");
		rowCnt++;
		Calendar ca = Calendar.getInstance();
		String date = (String) model.get("start_date");
		int thYear = Integer.parseInt(date.substring(0, 4));
		int thMonth = Integer.parseInt(date.substring(4, 6));
		ca.set(thYear, i, thMonth);
		int maxMon = ca.getActualMaximum(5);
		HSSFRow[] dataRow = new HSSFRow[maxMon];
		int[] sumOurArr = new int[maxMon];
		int[] sumOtherArr = new int[maxMon];

		for (i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			String inputDate = "";
			if (i < 9) {
				inputDate = date.concat("0").concat(Integer.toString(i + 1));
			} else {
				inputDate = date.concat(Integer.toString(i + 1));
			}

			dataRow[i].createCell(0).setCellValue(inputDate);

			for (int k = 0; k < data.size(); ++k) {
				RecordDto record = (RecordDto) data.get(k);
				if (inputDate.equals(record.getString("KEY_DATE"))) {
					dataRow[i].createCell(1).setCellValue((double) record.getInt("CNT1"));
					dataRow[i].createCell(2).setCellValue((double) record.getInt("CNT2"));
					sum1 += record.getInt("CNT1");
					sum2 += record.getInt("CNT2");
					break;
				}

				dataRow[i].createCell(1).setCellValue(0.0);
				dataRow[i].createCell(2).setCellValue(0.0);
			}
		}

		headrow2.createCell(0).setCellValue("계");
		headrow2.createCell(1).setCellValue((double) sum1);
		headrow2.createCell(2).setCellValue((double) sum2);
    }

	protected void muJebo(Map model, HSSFWorkbook wb) {
		int rowCnt = 0;
		List headListMdata = (List) model.get("headList");
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		sheet1.createRow(rowCnt);
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
		headrow2.createCell(0).setCellValue("ID");
		headrow2.createCell(1).setCellValue("이 름");
		headrow2.createCell(2).setCellValue("유형");
		headrow2.createCell(3).setCellValue("소속");
		headrow2.createCell(4).setCellValue("전화번호");
		headrow2.createCell(5).setCellValue("가입일자");

		for (int k = 1; k <= 12; ++k) {
			headrow2.createCell(5 + k).setCellValue(k + "월");
		}

		rowCnt++;
		HSSFRow[] dataRow1 = new HSSFRow[headListMdata.size()];
		int[] monArr = new int[12];

		for (int i = 0; i < dataRow1.length; ++i) {
			RecordDto record = (RecordDto) headListMdata.get(i);
			dataRow1[i] = sheet1.createRow(rowCnt + i);
			dataRow1[i].createCell(0).setCellValue(record.getString("ACT_ID"));
			dataRow1[i].createCell(1).setCellValue(record.getString("INFORMER_NAME"));
			dataRow1[i].createCell(2).setCellValue("통신원");
			dataRow1[i].createCell(3).setCellValue(record.getString("ORG_NAME"));
			dataRow1[i].createCell(4).setCellValue(record.getString("PHONE_CELL"));
			dataRow1[i].createCell(5).setCellValue(record.getString("REG_DATE"));

			for (int k = 0; k < dataList.size(); ++k) {
				RecordDto record2 = (RecordDto) dataList.get(k);

				for (int j = 1; j <= 12; ++j) {
					String j2 = "";
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
								dataRow1[i].createCell(5 + j).setCellValue((double) inputNum);
							}
						}

						monArr[j - 1] += inputNum;
					}
				}
			}
		}

	}

	protected void muJebo2(Map model, HSSFWorkbook wb) {
		int rowCnt = 0;
		List headListMdata = (List) model.get("headList");
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		headrow.createCell(0).setCellValue("총 인원 : " + headListMdata.size() + "명");
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
		headrow2.createCell(0).setCellValue("ID");
		headrow2.createCell(1).setCellValue("이 름");
		headrow2.createCell(2).setCellValue("유형");
		headrow2.createCell(3).setCellValue("소속");
		headrow2.createCell(4).setCellValue("전화번호");
		headrow2.createCell(5).setCellValue("가입일자");
		rowCnt++;
		HSSFRow[] dataRow1 = new HSSFRow[headListMdata.size()];
		int[] monArr = new int[12];

		for (int i = 0; i < dataRow1.length; ++i) {
			RecordDto record = (RecordDto) headListMdata.get(i);
			dataRow1[i] = sheet1.createRow(rowCnt + i);
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

	protected void informUse(HSSFWorkbook wb, String sheetNames, List monthReceiptMain, List monthOurReceiptMain,
			List monthOtherReceiptMain, List monthInformerMain, List monthInformer1InformMain, String thDateTime) {
		int rowCnt = 0;
		HSSFSheet sheet1 = wb.createSheet(sheetNames);
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue("통신원 제보건수 및 접수요원 가공건수");
		rowCnt++;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		rowCnt++;
		HSSFRow rbRow = sheet1.createRow(rowCnt);
		rowCnt++;
		HSSFRow rbRow1 = sheet1.createRow(rowCnt);
		headrow.createCell(1).setCellValue("총 제보건수");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 2, rowCnt - 2, 1, 2));
		headrow.createCell(3).setCellValue(sheetNames);
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 2, rowCnt - 2, 3, 8));
		rbRow.createCell(1).setCellValue("월간");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 1, 1));
		rbRow.createCell(2).setCellValue("일간");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 2, 2));
		rbRow.createCell(3).setCellValue("제보건수(건)");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt - 1, 3, 4));
		rbRow.createCell(5).setCellValue("총인원(명)");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 5, 5));
		rbRow.createCell(6).setCellValue("1인당 제보건수(총인원 대비)");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 6, 6));
		rbRow.createCell(7).setCellValue("1건이상 제보자수");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 7, 7));
		rbRow.createCell(8).setCellValue("1인당 제보건수(1건이상 제보자대비)");
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt, 8, 8));
		rbRow1.createCell(3).setCellValue("자국");
		rbRow1.createCell(4).setCellValue("타국");
		int monthCnt = 12;
		rowCnt++;
		HSSFRow[] dataRow = new HSSFRow[monthCnt];
		int[] maxDay = new int[monthCnt];

		int i;
		int statDate;
		for (i = 0; i < monthCnt; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt);
			dataRow[i].createCell(0).setCellValue(i + 1 + "월");
			Calendar ca = Calendar.getInstance();
			int thYear = Integer.parseInt(thDateTime.substring(0, 4));
			statDate = Integer.parseInt(thDateTime.substring(4, 6));
			ca.set(thYear, i, statDate);
			maxDay[i] = ca.getActualMaximum(5);
			rowCnt++;
		}

		DecimalFormat df = new DecimalFormat("##0.0");

		int j;
		RecordDto record;
		for (i = 0; i < monthReceiptMain.size(); ++i) {
			record = (RecordDto) monthReceiptMain.get(i);
			statDate = Integer.parseInt(record.getString("STAT_MONTH").substring(4, 6));

			for (j = 0; j < monthCnt; ++j) {
				if (j == statDate - 1) {
					dataRow[statDate - 1].createCell(1).setCellValue((double) record.getInt("CNT"));
					dataRow[statDate - 1].createCell(2)
							.setCellValue(df.format(record.getDouble("CNT") / (double) maxDay[j]));
				} else {
					dataRow[j].createCell(1).setCellValue(0.0);
					dataRow[j].createCell(2).setCellValue(0.0);
				}
			}
		}

		this.setRowData(monthOurReceiptMain, monthCnt, dataRow, 3);
		this.setRowData(monthOtherReceiptMain, monthCnt, dataRow, 4);

		for (i = 0; i < monthInformerMain.size(); ++i) {
			record = (RecordDto) monthInformerMain.get(i);
			statDate = Integer.parseInt(record.getString("STAT_MONTH").substring(4, 6));

			for (j = 0; j < monthCnt; ++j) {
				if (j == statDate - 1) {
					dataRow[statDate - 1].createCell(5).setCellValue((double) record.getInt("CNT"));
					dataRow[statDate - 1].createCell(6).setCellValue(df
							.format(dataRow[statDate - 1].getCell(1).getNumericCellValue() / record.getDouble("CNT")));
				} else {
					dataRow[j].createCell(5).setCellValue(0.0);
					dataRow[j].createCell(6).setCellValue(0.0);
				}
			}
		}

		for (i = 0; i < monthInformer1InformMain.size(); ++i) {
			record = (RecordDto) monthInformer1InformMain.get(i);
			statDate = Integer.parseInt(record.getString("STAT_MONTH").substring(4, 6));

			for (j = 0; j < monthCnt; ++j) {
				if (j == statDate - 1) {
					dataRow[statDate - 1].createCell(7).setCellValue((double) record.getInt("CNT"));
					dataRow[statDate - 1].createCell(8).setCellValue(df
							.format(dataRow[statDate - 1].getCell(1).getNumericCellValue() / record.getDouble("CNT")));
				} else {
					dataRow[j].createCell(7).setCellValue(0.0);
					dataRow[j].createCell(8).setCellValue(0.0);
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
		int rowCnt = 0;
		String thDateTime = (String) model.get("start_date");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue("교통정보 수집건수 및 활용실적");
		++rowCnt;
		HSSFRow mainRow = sheet1.createRow(rowCnt);
		mainRow.createCell(rowCnt).setCellValue(model.get("sheetNames1").toString());
		++rowCnt;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		headrow.createCell(0).setCellValue("구분");
		headrow.createCell(1).setCellValue("계");
		List headData = (List) model.get("headList");

		int j;
		for (j = 0; j < headData.size(); ++j) {
			RecordDto record = (RecordDto) headData.get(j);
			headrow.createCell(j * 2 + 3).setCellValue(record.getString("CODE_NAME"));
		}

		++rowCnt;
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		List headData1 = (List) model.get("useAllList");
		int sum = 0;

		for (j = 0; j < headData1.size(); ++j) {
			RecordDto record = (RecordDto) headData1.get(j);
			headrow1.createCell(j * 2 + 3).setCellValue((double) record.getInt("CNT"));
			sum += record.getInt("CNT");
		}

		headrow1.createCell(1).setCellValue((double) sum);

		int i;
		for (i = 0; i < headData.size() + 1; ++i) {
			sheet1.addMergedRegion(new CellRangeAddress(rowCnt - 1, rowCnt - 1, i * 2 + 1, i * 2 + 2));
			sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, i * 2 + 1, i * 2 + 2));
		}

		++rowCnt;
		headrow1 = sheet1.createRow(rowCnt);

		for (j = 0; j < headData.size() + 1; ++j) {
			headrow1.createCell(j * 2 + 1).setCellValue("자국");
			headrow1.createCell(j * 2 + 2).setCellValue("타국");
		}

		++rowCnt;
		headrow1 = sheet1.createRow(rowCnt);
		headData1 = (List) model.get("useOurOther");
		int sumOur = 0;
		int sumOther = 0;

		for (j = 0; j < headData1.size(); ++j) {
			RecordDto record = (RecordDto) headData1.get(j);
			headrow1.createCell(j + 3).setCellValue((double) record.getInt("CNT"));
			if (record.getString("REGION_ID").equals("000")) {
				sumOther += record.getInt("CNT");
			} else {
				sumOur += record.getInt("CNT");
			}
		}

		headrow1.createCell(1).setCellValue((double) sumOur);
		headrow1.createCell(2).setCellValue((double) sumOther);
		++rowCnt;
		Calendar ca = Calendar.getInstance();
		int thYear = Integer.parseInt(thDateTime.substring(0, 4));
		int thMonth = Integer.parseInt(thDateTime.substring(4, 6));
		ca.set(thYear, i, thMonth);
		int maxMon = ca.getActualMaximum(5);
		String date = (String) model.get("start_date");
		HSSFRow[] dataRow = new HSSFRow[maxMon];
		int[] sumOurArr = new int[maxMon];
		int[] sumOtherArr = new int[maxMon];

		for (i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			sumOurArr[i] = 0;
			sumOtherArr[i] = 0;
			if (i < 9) {
				dataRow[i].createCell(0).setCellValue(date.concat("0").concat(Integer.toString(i + 1)));
			} else {
				dataRow[i].createCell(0).setCellValue(date.concat(Integer.toString(i + 1)));
			}
		}

		List data = (List) model.get("useDaily");

		String regionId;
		int statDate;
		for (i = 0; i < data.size(); ++i) {
			RecordDto record = (RecordDto) data.get(i);
			statDate = Integer.parseInt(record.getString("STAT_DATE").substring(6, 8));
			regionId = record.getString("REGION_ID");
			String informerType = record.getString("INFORMER_TYPE");

			for (j = 0; j < headData.size(); ++j) {
				RecordDto record2 = (RecordDto) headData.get(j);
				if (informerType.equals(record2.getString("CODE"))) {
					if (regionId.equals("000")) {
						dataRow[statDate - 1].createCell(j * 2 + 4).setCellValue((double) record.getInt("CNT"));
						sumOtherArr[statDate - 1] += record.getInt("CNT");
					} else {
						dataRow[statDate - 1].createCell(j * 2 + 3).setCellValue((double) record.getInt("CNT"));
						sumOurArr[statDate - 1] += record.getInt("CNT");
					}
					//break;
				}
			}
		}

		for (i = 0; i < maxMon; ++i) {
			dataRow[i].createCell(1).setCellValue((double) sumOurArr[i]);
			dataRow[i].createCell(2).setCellValue((double) sumOtherArr[i]);
		}

		rowCnt = 0;
		HSSFSheet sheet2 = wb.createSheet(model.get("sheetNames2").toString());
		titlerow = sheet2.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("sheetNames2").toString());
		rowCnt = rowCnt + 1;
		headrow = sheet2.createRow(rowCnt);
		headrow.createCell(0).setCellValue("구분");
		headrow.createCell(1).setCellValue("계");
		headData = (List) model.get("regionHead");

		for (j = 0; j < headData.size(); ++j) {
			RecordDto record = (RecordDto) headData.get(j);
			headrow.createCell(j * 4 + 5).setCellValue(record.getString("CODE_NAME"));
		}

		for (i = 0; i < headData.size() + 1; ++i) {
			sheet2.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, i * 4 + 1, i * 4 + 4));
		}

		++rowCnt;
		headrow1 = sheet2.createRow(rowCnt);
		headData1 = (List) model.get("sumMonth");
		sum = 0;
		int sumMid = 0;
		List<Integer> sumMidList = new ArrayList();

		for (j = 0; j < headData1.size(); ++j) {
			RecordDto record = (RecordDto) headData1.get(j);
			if (j % 4 == 0 && j > 0) {
				sumMidList.add(sumMid);
				sumMid = 0;
				sumMid = sumMid + record.getInt("CNT");
			} else if (j + 1 == headData1.size()) {
				sumMid += record.getInt("CNT");
				sumMidList.add(sumMid);
			} else {
				sumMid += record.getInt("CNT");
			}

			sum += record.getInt("CNT");
		}

		headrow1.createCell(1).setCellValue((double) sum);

		int sumSendY;
		for (sumSendY = 0; sumSendY < sumMidList.size(); ++sumSendY) {
			headrow1.createCell(sumSendY * 4 + 5).setCellValue((double) (Integer) sumMidList.get(sumSendY));
		}

		for (i = 0; i < headData.size() + 1; ++i) {
			sheet2.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, i * 4 + 1, i * 4 + 4));
		}

		++rowCnt;
		headrow1 = sheet2.createRow(rowCnt);

		for (j = 0; j < headData.size() + 1; ++j) {
			headrow1.createCell(j * 4 + 1).setCellValue("방송자료");
			headrow1.createCell(j * 4 + 2).setCellValue("자체처리");
			headrow1.createCell(j * 4 + 3).setCellValue("안내");
			headrow1.createCell(j * 4 + 4).setCellValue("기관통보");
		}

		++rowCnt;
		headrow1 = sheet2.createRow(rowCnt);
		headData1 = (List) model.get("sumMonth");
		sumSendY = 0;
		int sumSendN = 0;
		int sumA07 = 0;
		int sumA08 = 0;

		String titleStr;
		for (i = 0; i < headData1.size(); ++i) {
			RecordDto record = (RecordDto) headData1.get(i);
			regionId = record.getString("CODE");
			titleStr = record.getString("SEQ");

			for (j = 0; j < headData.size(); ++j) {
				RecordDto recordType = (RecordDto) headData.get(j);
				if (recordType.getString("CODE").equals(regionId)) {
					if (titleStr.equals("B")) {
						headrow1.createCell(j * 4 + 5).setCellValue((double) record.getInt("CNT"));
						sumSendY += record.getInt("CNT");
					} else if (titleStr.equals("NB")) {
						headrow1.createCell(j * 4 + 6).setCellValue((double) record.getInt("CNT"));
						sumSendN += record.getInt("CNT");
					} else if (titleStr.equals("A07")) {
						headrow1.createCell(j * 4 + 7).setCellValue((double) record.getInt("CNT"));
						sumA07 += record.getInt("CNT");
					} else if (titleStr.equals("A08")) {
						headrow1.createCell(j * 4 + 8).setCellValue((double) record.getInt("CNT"));
						sumA08 += record.getInt("CNT");
					}
				}
			}
		}

		headrow1.createCell(1).setCellValue((double) sumSendY);
		headrow1.createCell(2).setCellValue((double) sumSendN);
		headrow1.createCell(3).setCellValue((double) sumA07);
		headrow1.createCell(4).setCellValue((double) sumA08);
		++rowCnt;
		HSSFRow[] dataRow1 = new HSSFRow[maxMon];
		int[] sumSendYArr = new int[maxMon];
		int[] sumSendNArr = new int[maxMon];
		int[] sumA07Arr = new int[maxMon];
		int[] sumA08Arr = new int[maxMon];

		for (i = 0; i < maxMon; ++i) {
			dataRow1[i] = sheet2.createRow(rowCnt + i);
			sumSendYArr[i] = 0;
			sumSendNArr[i] = 0;
			sumA07Arr[i] = 0;
			sumA08Arr[i] = 0;
			if (i < 9) {
				dataRow1[i].createCell(0).setCellValue(date.concat("0").concat(Integer.toString(i + 1)));
			} else {
				dataRow1[i].createCell(0).setCellValue(date.concat(Integer.toString(i + 1)));
			}
		}

		data = (List) model.get("Data");

		for (int k = 0; k < dataRow1.length; ++k) {
			for (j = 0; j < headData.size(); ++j) {
				RecordDto recordType = (RecordDto) headData.get(j);

				for (i = 0; i < data.size(); ++i) {
					RecordDto record = (RecordDto) data.get(i);
					statDate = Integer.parseInt(record.getString("STAT_DATE").substring(6, 8));
					regionId = record.getString("INFORMER_TYPE");
					titleStr = record.getString("SEQ");
					if (statDate - 1 == k) {
						if (regionId.equals(recordType.getString("CODE"))) {
							if (titleStr.equals("B")) {
								dataRow1[statDate - 1].createCell(j * 4 + 5)
										.setCellValue((double) record.getInt("CNT"));
								sumSendYArr[statDate - 1] += record.getInt("CNT");
							} else if (titleStr.equals("NB")) {
								dataRow1[statDate - 1].createCell(j * 4 + 6)
										.setCellValue((double) record.getInt("CNT"));
								sumSendNArr[statDate - 1] += record.getInt("CNT");
							} else if (titleStr.equals("A07")) {
								dataRow1[statDate - 1].createCell(j * 4 + 7)
										.setCellValue((double) record.getInt("CNT"));
								sumA07Arr[statDate - 1] += record.getInt("CNT");
							} else if (titleStr.equals("A08")) {
								dataRow1[statDate - 1].createCell(j * 4 + 8)
										.setCellValue((double) record.getInt("CNT"));
								sumA08Arr[statDate - 1] += record.getInt("CNT");
							}
						}
					} else {
						if (dataRow1[k].getCell(j * 4 + 5) == null) {
							dataRow1[k].createCell(j * 4 + 5).setCellValue(0.0);
							sumSendYArr[k] += 0;
						}

						if (dataRow1[k].getCell(j * 4 + 6) == null) {
							dataRow1[k].createCell(j * 4 + 6).setCellValue(0.0);
							sumSendYArr[k] += 0;
						}

						if (dataRow1[k].getCell(j * 4 + 7) == null) {
							dataRow1[k].createCell(j * 4 + 7).setCellValue(0.0);
							sumSendYArr[k] += 0;
						}

						if (dataRow1[k].getCell(j * 4 + 8) == null) {
							dataRow1[k].createCell(j * 4 + 8).setCellValue(0.0);
							sumSendYArr[k] += 0;
						}
					}
				}
			}
		}

		for (i = 0; i < maxMon; ++i) {
			dataRow1[i].createCell(1).setCellValue((double) sumSendYArr[i]);
			dataRow1[i].createCell(2).setCellValue((double) sumSendNArr[i]);
			dataRow1[i].createCell(3).setCellValue((double) sumA07Arr[i]);
			dataRow1[i].createCell(4).setCellValue((double) sumA08Arr[i]);
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
		int rowCnt = 0;
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, 12));
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		headrow.createCell(0).setCellValue(model.get("start_date").toString());
		sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, 12));
		rowCnt++;
		int cellCnt = 4;
		int nwRowCnt = rowCnt;
		int addCellCnt = 0;
		int bfMonSum = 0;
		int thMonSum = 0;
		int addSubSum = 0;
		int wiSum = 0;
		int haeSum = 0;

		for (int k = 0; k < dataList.size(); ++k) {
			RecordDto record = (RecordDto) dataList.get(k);
			String bfType = "";
			String nwType = record.getString("IND_NAME");
			if (k > 0) {
				RecordDto rcd = (RecordDto) dataList.get(k - 1);
				bfType = rcd.getString("IND_NAME");
				if (!nwType.equals(bfType)) {
					rowCnt = nwRowCnt + 1;
					sheet1.getRow(rowCnt).createCell(2).setCellValue((double) bfMonSum);
					rowCnt++;
					sheet1.getRow(rowCnt).createCell(2).setCellValue((double) thMonSum);
					rowCnt++;
					sheet1.getRow(rowCnt).createCell(2).setCellValue((double) addSubSum);
					rowCnt++;
					sheet1.getRow(rowCnt).createCell(3).setCellValue((double) wiSum);
					rowCnt++;
					sheet1.getRow(rowCnt).createCell(3).setCellValue((double) haeSum);
					bfMonSum = 0;
					thMonSum = 0;
					addSubSum = 0;
					wiSum = 0;
					haeSum = 0;
					rowCnt = nwRowCnt + 7;
					cellCnt = 4;
					addCellCnt = 0;
					sheet1.createRow(rowCnt).createCell(0).setCellValue(record.getInt("IND_TYPE") + 1 + "." + nwType);
					sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, 1));
					rowCnt++;
					nwRowCnt = rowCnt;
				} else {
					rowCnt = nwRowCnt;
				}
			} else {
				sheet1.createRow(nwRowCnt).createCell(0).setCellValue(record.getInt("IND_TYPE") + 1 + "." + nwType);
				sheet1.addMergedRegion(new CellRangeAddress(nwRowCnt, nwRowCnt, 0, 1));
				rowCnt = nwRowCnt + 1;
				nwRowCnt = rowCnt;
			}

			if (sheet1.getRow(rowCnt) == null) {
				sheet1.createRow(rowCnt).createCell(1).setCellValue("구분");
				sheet1.getRow(rowCnt).createCell(2).setCellValue("계");
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 2, 3));
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("ORG_NAME"));
				rowCnt++;
				sheet1.createRow(rowCnt).createCell(1).setCellValue("전월");
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 2, 3));
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT1"));
				rowCnt++;
				sheet1.createRow(rowCnt).createCell(1).setCellValue("금월");
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 2, 3));
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT2"));
				rowCnt++;
				sheet1.createRow(rowCnt).createCell(1).setCellValue("증감");
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 2, 3));
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT3"));
				rowCnt++;
				sheet1.createRow(rowCnt).createCell(1).setCellValue("비고");
				sheet1.getRow(rowCnt).createCell(2).setCellValue("위촉");
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT4"));
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt + 1, 1, 1));
				rowCnt++;
				sheet1.createRow(rowCnt).createCell(2).setCellValue("해촉");
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT5"));
			} else {
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("ORG_NAME"));
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT1"));
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT2"));
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT3"));
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT4"));
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(addCellCnt + cellCnt).setCellValue(record.getString("CNT5"));
			}

			bfMonSum += record.getInt("CNT1");
			thMonSum += record.getInt("CNT2");
			addSubSum += record.getInt("CNT3");
			wiSum += record.getInt("CNT4");
			haeSum += record.getInt("CNT5");
			++addCellCnt;
			if (k == dataList.size() - 1) {
				rowCnt = nwRowCnt + 1;
				sheet1.getRow(rowCnt).createCell(2).setCellValue((double) bfMonSum);
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(2).setCellValue((double) thMonSum);
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(2).setCellValue((double) addSubSum);
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(3).setCellValue((double) wiSum);
				rowCnt++;
				sheet1.getRow(rowCnt).createCell(3).setCellValue((double) haeSum);
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
		int rowCnt = 0;
		int gl = 0;
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		HSSFRow headrow = sheet1.createRow(rowCnt);
		headrow.createCell(0).setCellValue("실적날짜");
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
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
		rowCnt++;
		HSSFRow[] dataRow1 = new HSSFRow[dataList.size()];

		for (int i = 0; i < dataRow1.length; ++i) {
			RecordDto record = (RecordDto) dataList.get(i);
			dataRow1[i] = sheet1.createRow(rowCnt);
			if (!record.getString("ACT_ID").equals("sum")) {
				if (model.get("sheetNames1").toString().equals("가스기술공사 제보실적")) {
					dataRow1[i].createCell(0).setCellValue((double) (i + 1));
					dataRow1[i].createCell(1).setCellValue(record.getString("ACT_ID"));
					dataRow1[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
					dataRow1[i].createCell(3).setCellValue(record.getString("SUM_CNT"));
				} else {
					dataRow1[i].createCell(0).setCellValue((double) (i + 1));
					dataRow1[i].createCell(1).setCellValue(record.getString("ACT_ID"));
					dataRow1[i].createCell(2).setCellValue(record.getString("INFORMER_NAME"));
					dataRow1[i].createCell(3).setCellValue(record.getString("PHONE_CELL"));
					dataRow1[i].createCell(4).setCellValue(record.getString("SUM_CNT"));
				}
			} else {
				dataRow1[i].createCell(0).setCellValue("합      계");
				sheet1.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, gl - 1));
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
			rowCnt++;
		}
    }

    protected void informerDown(Map model, HSSFWorkbook wb) {
		int rowCnt = 0;
		List dataList = (List) model.get("Data");
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		rowCnt++;
		HSSFRow headrow0 = sheet1.createRow(rowCnt);
		headrow0.createCell(0).setCellValue("합계 : " + dataList.size() + "건");
		rowCnt++;
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		headrow1.createCell(0).setCellValue("ID");
		headrow1.createCell(1).setCellValue("방송국");
		headrow1.createCell(2).setCellValue("유형");
		headrow1.createCell(3).setCellValue("소속기관");
		headrow1.createCell(4).setCellValue("이름");
		headrow1.createCell(5).setCellValue("전화");
		headrow1.createCell(6).setCellValue("활동여부");
		headrow1.createCell(7).setCellValue("등록일");
		rowCnt++;
		HSSFRow[] dataRow = new HSSFRow[dataList.size()];

		for (int i = 0; i < dataList.size(); ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
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
    

    protected void orgOrgSub(Map model, HSSFWorkbook wb) {
        List dataList = (List) model.get("orgOrgSub");
        List eraList = (List) model.get("eraList");
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
        headrow1.createCell(4).setCellValue("중분류");
        headrow1.createCell(5).setCellValue("소분류");
        headrow1.createCell(6).setCellValue("생일");
        headrow1.createCell(7).setCellValue("월계");
        
        int nxtCnt =7;
        
        for (int i = 1; i <= eraList.size(); i++) {
        	 RecordDto record1 = (RecordDto) eraList.get(i-1);
        	 String key_date = record1.getString("KEY_DATE");
        	 key_date=key_date.replaceAll("date_", "");
        	 key_date=key_date.substring(0, 4)+"년 "+key_date.substring(4, 6)+"월";
        	 headrow1.createCell(nxtCnt+i).setCellValue(key_date);
		}
        
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
            dataRow[i].createCell(5).setCellValue(record.getString("ORG_SNAME"));
            dataRow[i].createCell(6).setCellValue(record.getString("BIRTHDAY"));
            dataRow[i].createCell(7).setCellValue(record.getString("SUM_CNT"));
            
//            for (int j = 1; j <= 31; j++) {
//            	dataRow[i].createCell(nxtCnt+j).setCellValue(record.getString("D"+j));
//            }
            for (int j = 1; j <= eraList.size(); j++) {
	           	 RecordDto record2 = (RecordDto) eraList.get(j-1);
	           	 String key_date = record2.getString("KEY_DATE");
	           	dataRow[i].createCell(nxtCnt+j).setCellValue(record.getString(key_date));
	   		}
        }
    }
    
    
}