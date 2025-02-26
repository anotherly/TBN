package kr.co.wizbrain.tbn.comm.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.AbstractView;

import kr.co.wizbrain.tbn.comm.RecordDto;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;
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
            } else if (model.get("mapping").equals("informerReport")) {
            	informerReport(model, wb);
            } else if(model.get("mapping").equals("addDownload")) {
            	addDownload(model, wb);
            } else if (model.get("mapping").equals("standardInformerTypeTime")) {
            	standardInformerTypeTime(model, wb);
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
    
	
	// 긴급 교통정보 방송 현황 분석
    protected void extrBro(Map model, HSSFWorkbook wb) {
    	
    	// 기본 변수 생성
 		int i = 0;
		int j = 0;
		int rowCnt = 0;
		
		// 엑셀 시트 생성
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		
		// 타이틀 셀에 삽입 => 긴급교통정보 처리건수 실적 (1행)
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		
		rowCnt++;
		rowCnt++;
		rowCnt++;
		
		HSSFRow headrow1 = sheet1.createRow(rowCnt);
		
		// 일자, 총 긴급정보 건수, 5분내 방송처리 건수 (4행)
		headrow1.createCell(0).setCellValue("일자");
		headrow1.createCell(1).setCellValue("총 긴급정보 건수");
		headrow1.createCell(2).setCellValue("5분내 방송처리 건수");
		
		rowCnt++;
		HSSFRow headrow2 = sheet1.createRow(rowCnt);
		
		int sum1 = 0;
		int sum2 = 0;
		
		// 시작일 ~ 종료일 까지 데이터 가져오기 (cnt 등)
		List data = (List) model.get("Data");
		
		rowCnt++;
		Calendar ca = Calendar.getInstance();

		// 시작일, 종료일 가져오기
		String Sdate = (String) model.get("start_date");
		String Edate = (String) model.get("end_date");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate startDate = LocalDate.parse(Sdate, formatter);
		LocalDate endDate = LocalDate.parse(Edate, formatter);
		
		int maxMon = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;

	    List<String> dateList = new ArrayList<>(maxMon);

	    LocalDate currentDate = startDate;
	    while (!currentDate.isAfter(endDate)) {
	    	dateList.add(currentDate.format(formatter)); 
	        currentDate = currentDate.plusDays(1); 
	    }

	    HSSFRow[] dataRow = new HSSFRow[maxMon];
			
		for (i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			String inputDate = "";
			
			dataRow[i].createCell(0).setCellValue(dateList.get(i));
			inputDate = dateList.get(i);
			
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

		// 합계 넣기 (5행)
		headrow2.createCell(0).setCellValue("계");
		headrow2.createCell(1).setCellValue((double) sum1);
		headrow2.createCell(2).setCellValue((double) sum2);
    }

    protected void disastorStat(Map model, HSSFWorkbook wb) {
		int i = 0;
		int j = 0;
		int rowCnt = 0;
		
		// 시트 생성
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		
		// 타이틀 삽입 (1행)
		titlerow.createCell(rowCnt).setCellValue(model.get("titleName").toString());
		
		rowCnt++;
		rowCnt++;
		rowCnt++;
		
		// 일자, 일별 총 제보건수, 재난제보 건수 (4행)
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
		
		// 시작일, 종료일 가져오기
		String Sdate = (String) model.get("start_date");
		String Edate = (String) model.get("end_date");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate startDate = LocalDate.parse(Sdate, formatter);
		LocalDate endDate = LocalDate.parse(Edate, formatter);
		
		int maxMon = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;

	    List<String> dateList = new ArrayList<>(maxMon);

	    LocalDate currentDate = startDate;
	    while (!currentDate.isAfter(endDate)) {
	    	dateList.add(currentDate.format(formatter)); 
	        currentDate = currentDate.plusDays(1); 
	    }

	    HSSFRow[] dataRow = new HSSFRow[maxMon];

		for (i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			String inputDate = "";
			
			dataRow[i].createCell(0).setCellValue(dateList.get(i));
			inputDate = dateList.get(i);

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

		
		// 합계 (5행)
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
		
		int eSize = (int)model.get("eSize");
		
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
		
		List<Integer> cntValues = new ArrayList<>();

		int sum = 0;
		//통신원 유형별 합계부분
		for (j = 0; j < headData1.size(); ++j) {
			RecordDto record = (RecordDto) headData1.get(j);
			headrow1.createCell(j * 2 + 3).setCellValue((double) record.getInt("CNT"));
			 cntValues.add(record.getInt("CNT"));
			sum += record.getInt("CNT");
		}

		headrow1.createCell(1).setCellValue((double) sum);

		int i;
		//통신원 유형별 합계 병합부분
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
		
		// 24-12-16 : 시작일 가져오기
		String Sdate = (String) model.get("start_date");
		String Edate = (String) model.get("end_date");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate startDate = LocalDate.parse(Sdate, formatter);
		LocalDate endDate = LocalDate.parse(Edate, formatter);
		
		 int maxMon = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;

	     List<String> dateList = new ArrayList<>(maxMon);

	     LocalDate currentDate = startDate;
	     while (!currentDate.isAfter(endDate)) {
	    	 dateList.add(currentDate.format(formatter)); 
	         currentDate = currentDate.plusDays(1); 
	     }
		
		HSSFRow[] dataRow = new HSSFRow[maxMon];
		int[] sumOurArr = new int[maxMon];
		int[] sumOtherArr = new int[maxMon];

		for(i = 0; i < maxMon; ++i) {
			dataRow[i] = sheet1.createRow(rowCnt + i);
			sumOurArr[i] = 0;
			sumOtherArr[i] = 0;
			
			dataRow[i].createCell(0).setCellValue(dateList.get(i));
		}
		
		
		
/*		for (i = 0; i < maxMon; ++i) 
			dataRow[i] = sheet1.createRow(rowCnt + i);
			sumOurArr[i] = 0;
			sumOtherArr[i] = 0;
			if (i < 9) {
				dataRow[i].createCell(0).setCellValue(date.concat("0").concat(Integer.toString(i + 1)));
			} else {
				dataRow[i].createCell(0).setCellValue(date.concat(Integer.toString(i + 1)));
			}
		}*/

		List data = (List) model.get("useDaily");

		String regionId;
		int statDate;
		int standardDate = Integer.parseInt(Sdate.substring(6, 8));
		int dataSize = data.size();
		
    	for (int k = 0; k < maxMon; k++) {
    		/*logger.debug(dateList.get(k));*/

    		for (i = 0; i < data.size(); ++i) {
				RecordDto record = (RecordDto) data.get(i);
				statDate = Integer.parseInt(record.getString("STAT_DATE").substring(6, 8));
				regionId = record.getString("REGION_ID");
				String informerType = record.getString("INFORMER_TYPE");
				
				String getStatDate = record.getString("STAT_DATE");
				
				int size = maxMon;
				int mSize = size;
				for (j = 0; j < headData.size(); ++j) {
					if(getStatDate.equals(dateList.get(k))){
						RecordDto record2 = (RecordDto) headData.get(j);
						if (informerType.equals(record2.getString("CODE"))) {
							if (regionId.equals("000")) {
								dataRow[k].createCell(j * 2 + 4).setCellValue((double) record.getInt("CNT"));
								sumOtherArr[k] += record.getInt("CNT");
							} else {
								dataRow[k].createCell(j * 2 + 3).setCellValue((double) record.getInt("CNT"));
								sumOurArr[k] += record.getInt("CNT");
							}
							//break;
						}
					}
				}
			}
		}

		for (i = 0; i < maxMon; ++i) {
			dataRow[i].createCell(1).setCellValue((double) sumOurArr[i]);
			dataRow[i].createCell(2).setCellValue((double) sumOtherArr[i]);
		}
		rowCnt = 0;
		
		//활용실적 시작
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
		data = (List) model.get("Data");
		
		sum = 0;
		int sumMid = 0;
		List<Integer> sumMidList = new ArrayList();

		
		/*for (j = 0; j < data.size(); ++j) {
			RecordDto record = (RecordDto) data.get(j);
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
		}*/
		
		int[] sumList = new int[eSize];
		
		// 구분 > 합계들 나누기 및 구하기
		for(j = 0; j < data.size(); j++) {
			RecordDto record = (RecordDto) data.get(j);		
			int informerType = record.getInt("INFORMER_TYPE"); // 해당 데이터의 통신원 유형 가져오기
			
			sumList[informerType] += record.getInt("CNT");
			sum += record.getInt("CNT");
		}
		
		// 구분 > 계 (3행)
		headrow1.createCell(1).setCellValue((double) sum);

		// 구분 > 통신원, 경찰제보처, 시민, 직원 등 합계 (3행)
		int sumSendY;
		
		// 수정 전 ( ArrayIndexOutOfBoundsException: 8 오류 발생하여 for 값을 8로 고정 )
		for (sumSendY = 0; sumSendY < cntValues.size(); ++sumSendY) {
			headrow1.createCell(sumSendY * 4 + 5).setCellValue(sumList[sumSendY]);
		}
		
		// 수정 후 값을 8로 고정시켜 sumSendY가 7을 넘지 않도록 설정
		/*for (sumSendY = 0; sumSendY < 8; ++sumSendY) {
			headrow1.createCell(sumSendY * 4 + 5).setCellValue(sumList[sumSendY]);
		}*/

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
/*		for (i = 0; i < headData1.size(); ++i) {
			RecordDto record = (RecordDto) headData1.get(i);
			RecordDto record = (RecordDto) data.get(i);
			regionId = record.getString("CODE");
			titleStr = record.getString("SEQ");

			for (j = 0; j < headData.size(); ++j) {
				RecordDto recordType = (RecordDto) headData.get(j);
				RecordDto recordType = (RecordDto) data.get(j);
				if (recordType.getString("CODE").equals(regionId)) {
					if (titleStr.equals("B")) {
						headrow1.createCell(j * 4 + 5).setCellValue("허허허허허허");
						sumSendY += record.getInt("CNT");
					} else if (titleStr.equals("NB")) {
						headrow1.createCell(j * 4 + 6).setCellValue("허허허허허허");
						sumSendN += record.getInt("CNT");
					} else if (titleStr.equals("A07")) {
						headrow1.createCell(j * 4 + 7).setCellValue("허허허허허허");
						sumA07 += record.getInt("CNT");
					} else if (titleStr.equals("A08")) {
						headrow1.createCell(j * 4 + 8).setCellValue("허허허허허허");
						sumA08 += record.getInt("CNT");
					}
				}
			}
		}*/
		
		int sSize = 4 * eSize;
		int[] sumList1 = new int[sSize];
		
		for(j = 0; j < data.size(); j++) {
			RecordDto record = (RecordDto) data.get(j);		
			int informerType = record.getInt("INFORMER_TYPE"); // 해당 데이터의 통신원 유형 가져오기
			String type = record.getString("SEQ");

			if(type.equals("B")) {
				sumList1[informerType * 4 + 0] += record.getInt("CNT");
				sumSendY += record.getInt("CNT");
 			} else if(type.equals("NB")) {
 				sumList1[informerType * 4 + 1] += record.getInt("CNT");
 				sumSendN += record.getInt("CNT");
 			} else if(type.equals("A07")) {
 				sumList1[informerType * 4 + 2] += record.getInt("CNT");
 				sumA07 += record.getInt("CNT");
 			} else {
 				sumList1[informerType * 4 + 3] += record.getInt("CNT");
 				sumA08 += record.getInt("CNT");
 			}
		}
		
		for(int z = 0; z < sSize ; z++) {
			headrow1.createCell(z + 5).setCellValue(sumList1[z]);
		}
		
		// 전체 분류별 합계
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
			
			// 날짜 넣기
			dataRow1[i].createCell(0).setCellValue(dateList.get(i));
		}


		for (int k = 0; k < dataRow1.length; ++k) {
			for (j = 0; j < headData.size(); ++j) {
				RecordDto recordType = (RecordDto) headData.get(j);

				for (i = 0; i < data.size(); ++i) {
					RecordDto record = (RecordDto) data.get(i);
					statDate = Integer.parseInt(record.getString("STAT_DATE").substring(6, 8));
					
					
					regionId = record.getString("INFORMER_TYPE");
					titleStr = record.getString("SEQ");
					String getStatDate = record.getString("STAT_DATE");
					
					
					// 아래 부터는 일자, 통신원 종류별 값임(합계 X)
					if(getStatDate.equals(dateList.get(k))) {
							if (regionId.equals(recordType.getString("CODE"))) {
								
								// 방송자료일 때
								if (titleStr.equals("B")) {
									dataRow1[k].createCell(j * 4 + 5)
											.setCellValue((double) record.getInt("CNT"));
									sumSendYArr[k] += record.getInt("CNT");
								} else if (titleStr.equals("NB")) { // 자체처리일 때
									dataRow1[k].createCell(j * 4 + 6)
											.setCellValue((double) record.getInt("CNT"));
									sumSendNArr[k] += record.getInt("CNT");
								} else if (titleStr.equals("A07")) { // 기관통보일 때
									dataRow1[k].createCell(j * 4 + 7)
											.setCellValue((double) record.getInt("CNT"));
									sumA07Arr[k] += record.getInt("CNT");
								} else if (titleStr.equals("A08")) { // 안내일 때
									dataRow1[k].createCell(j * 4 + 8)
											.setCellValue((double) record.getInt("CNT"));
									sumA08Arr[k] += record.getInt("CNT");
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

		// 구분 > 계 > 합계 넣기
		for (i = 0; i < maxMon; ++i) {
			dataRow1[i].createCell(1).setCellValue((double) sumSendYArr[i]);
			dataRow1[i].createCell(2).setCellValue((double) sumSendNArr[i]);
			dataRow1[i].createCell(3).setCellValue((double) sumA07Arr[i]);
			dataRow1[i].createCell(4).setCellValue((double) sumA08Arr[i]);
		}

	}
	
	/*protected void standartdReceiptUse(Map model, HSSFWorkbook wb) {
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
		
		// 24-12-16 : 시작일 가져오기
		String Sdate = (String) model.get("start_date");
		
		// 24-12-16 : 시작일 슬라이스
		String date = Sdate.substring(0, 6);
		
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

	}*/

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
    

    // 24-11-20 :제보자별 제보현황
    protected void informerReport(Map model, HSSFWorkbook wb) {
    	int rowCnt = 0;
		List dataList = (List) model.get("dataList");
		
		// 뽑으려는 통계 제목
		String title = "제보자별 제보현황";
				
		// 통계제목 밑 총 인원 텍스트 구하기
		Object allInforValget = model.get("allInformer");
		String allInformerVal = (allInforValget != null) ? allInforValget.toString() : "0";
		String allInformer = "총 인원 : " + allInformerVal +"명";
		
		// 통계 제목 밑 총 건수 텍스트 구하기
		/*String allReportVal = (String) model.get("allReport");
		String allReport = "총 건수 : " + allReportVal +"건";*/
		
		// 통계 제목 밑 일자 변환
		String getStartDate = (String) model.get("start_date");
		String startY = getStartDate.substring(0,4) + "년 ";
		String startM = getStartDate.substring(4,6) + "월 ";
		String startD = getStartDate.substring(6,8) + "일 ~ ";
				
		String startDate = startY + startM + startD;
				
		String getEndDate = (String) model.get("end_date");
		String endY = getEndDate.substring(0,4) + "년 ";
		String endM = getEndDate.substring(4,6) + "월 ";
		String endD = getEndDate.substring(6,8) + "일";
				
		String endDate = endY + endM + endD;
				
		HSSFSheet sheet1 = wb.createSheet(model.get("sheetNames1").toString());
		HSSFRow titlerow = sheet1.createRow(rowCnt);
		
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 26)); // 타이틀 셀 병합
		titlerow.createCell(0).setCellValue("제보자별 제보현황");// 제목 넣음
		
		rowCnt++; //1
		// 시작일 ~ 종료일
		HSSFRow daterow = sheet1.createRow(rowCnt);
		sheet1.addMergedRegion(new CellRangeAddress(1, 1, 0, 26));
		daterow.createCell(0).setCellValue(startDate + endDate);
		
		rowCnt++; //2
		// 공백 행 하나 생성
		HSSFRow emptyRow1 = sheet1.createRow(rowCnt);
		
		rowCnt++; //3
		// 총 인원수 및 총 건수 행 생성
		HSSFRow subTitle1 = sheet1.createRow(rowCnt);
		sheet1.addMergedRegion(new CellRangeAddress(2, 2, 0, 2)); // 총인원 수 셀 병합
		sheet1.addMergedRegion(new CellRangeAddress(2, 2, 4, 6)); // 총 건수 셀 병합
		
		subTitle1.createCell(0).setCellValue(allInformer);
		/*subTitle1.createCell(2).setCellValue(allReport);*/
		
		rowCnt++; // 4
		// 통계 열 만들기
		HSSFRow rowTitle1 = sheet1.createRow(rowCnt);
		rowCnt++; // 5
		HSSFRow rowTitle2 = sheet1.createRow(rowCnt);
		
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 0, 0)); // ID 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 1, 1)); // 통신원 이름 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 2, 2)); // 통신원 유형 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 3, 3)); // 통신원 소속 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 4, 4)); // 통신원 소속 상세 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 5, 5)); // 통신원 연락처 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 6, 6)); // 제보건수 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 7, 7)); // 방송건수 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 5, 8, 8)); // 주요제보 행 병합	
		sheet1.addMergedRegion(new CellRangeAddress(4, 4, 9, 17)); // 제보건수 상세 행 병합
		sheet1.addMergedRegion(new CellRangeAddress(4, 4, 18, 26)); // 방송건수 상세 행 병합
		
		rowTitle1.createCell(0).setCellValue("통신원 ID");
		rowTitle1.createCell(1).setCellValue("통신원 이름");
		rowTitle1.createCell(2).setCellValue("통신원 유형");
		rowTitle1.createCell(3).setCellValue("통신원 소속");
		rowTitle1.createCell(4).setCellValue("소속 상세");
		rowTitle1.createCell(5).setCellValue("연락처");
		rowTitle1.createCell(6).setCellValue("제보건수");
		rowTitle1.createCell(7).setCellValue("방송건수");
		rowTitle1.createCell(8).setCellValue("주요제보");
		rowTitle1.createCell(9).setCellValue("제보건수");
		rowTitle1.createCell(18).setCellValue("방송건수");
		
		// 제보건수 상세 열
		rowTitle2.createCell(9).setCellValue("원활");
		rowTitle2.createCell(10).setCellValue("정체");
		rowTitle2.createCell(11).setCellValue("공사");
		rowTitle2.createCell(12).setCellValue("행사");
		rowTitle2.createCell(13).setCellValue("사고");
		rowTitle2.createCell(14).setCellValue("기상");
		rowTitle2.createCell(15).setCellValue("기타");
		rowTitle2.createCell(16).setCellValue("안내");
		rowTitle2.createCell(17).setCellValue("기관통보");
		
		// 방송건수 상세 열
		rowTitle2.createCell(18).setCellValue("원활");
		rowTitle2.createCell(19).setCellValue("정체");
		rowTitle2.createCell(20).setCellValue("공사");
		rowTitle2.createCell(21).setCellValue("행사");
		rowTitle2.createCell(22).setCellValue("사고");
		rowTitle2.createCell(23).setCellValue("기상");
		rowTitle2.createCell(24).setCellValue("기타");
		rowTitle2.createCell(25).setCellValue("안내");
		rowTitle2.createCell(26).setCellValue("기관통보");
		
		rowCnt++;
		HSSFRow[] dataRow = new HSSFRow[dataList.size()];
		
		int allReport = 0;
		// 받아온 개수만큼 데이터 삽입
		for(int i = 0; i < dataList.size(); i++){
			dataRow[i] = sheet1.createRow(rowCnt + i);
			
			// dataList에서 각 row를 가져오고, 이를 Map으로 캐스팅
		    Map<String, Object> record = (Map<String, Object>) dataList.get(i);

		    int getReport = Integer.parseInt(record.get("제보건수").toString());		    
		    allReport = allReport + getReport;
		    
		    // record의 각 필드를 Map을 통해 접근
		    dataRow[i].createCell(0).setCellValue(record.get("통신원ID").toString());
		    dataRow[i].createCell(1).setCellValue(record.get("통신원이름").toString());
		    dataRow[i].createCell(2).setCellValue(/*record.get("유형").toString()*/ "통신원"); // 어차피 통신원(0)만 추출하기 때문에 필요 없음
		    dataRow[i].createCell(3).setCellValue(record.get("통신원소속").toString());
		    dataRow[i].createCell(4).setCellValue(record.get("소속상세").toString());
		    dataRow[i].createCell(5).setCellValue(record.get("연락처").toString());
		    dataRow[i].createCell(6).setCellValue(Integer.parseInt(record.get("제보건수").toString()));
		    dataRow[i].createCell(7).setCellValue(Integer.parseInt(record.get("방송건수").toString()));
		    dataRow[i].createCell(8).setCellValue(Integer.parseInt(record.get("주요제보").toString()));
		    dataRow[i].createCell(9).setCellValue(Integer.parseInt(record.get("제보건수_원활").toString()));
		    dataRow[i].createCell(10).setCellValue(Integer.parseInt(record.get("제보건수_정체").toString()));
		    dataRow[i].createCell(11).setCellValue(Integer.parseInt(record.get("제보건수_공사").toString()));
		    dataRow[i].createCell(12).setCellValue(Integer.parseInt(record.get("제보건수_행사").toString()));
		    dataRow[i].createCell(13).setCellValue(Integer.parseInt(record.get("제보건수_사고").toString()));
		    dataRow[i].createCell(14).setCellValue(Integer.parseInt(record.get("제보건수_기상").toString()));
		    dataRow[i].createCell(15).setCellValue(Integer.parseInt(record.get("제보건수_기타").toString()));
		    dataRow[i].createCell(16).setCellValue(Integer.parseInt(record.get("제보건수_안내").toString()));
		    dataRow[i].createCell(17).setCellValue(Integer.parseInt(record.get("제보건수_기관통보").toString()));
		    dataRow[i].createCell(18).setCellValue(Integer.parseInt(record.get("방송건수_원활").toString()));
		    dataRow[i].createCell(19).setCellValue(Integer.parseInt(record.get("방송건수_정체").toString()));
		    dataRow[i].createCell(20).setCellValue(Integer.parseInt(record.get("방송건수_공사").toString()));
		    dataRow[i].createCell(21).setCellValue(Integer.parseInt(record.get("방송건수_행사").toString()));
		    dataRow[i].createCell(22).setCellValue(Integer.parseInt(record.get("방송건수_사고").toString()));
		    dataRow[i].createCell(23).setCellValue(Integer.parseInt(record.get("방송건수_기상").toString()));
		    dataRow[i].createCell(24).setCellValue(Integer.parseInt(record.get("방송건수_기타").toString()));
		    dataRow[i].createCell(25).setCellValue(Integer.parseInt(record.get("방송건수_안내").toString()));
		    dataRow[i].createCell(26).setCellValue(Integer.parseInt(record.get("방송건수_기관통보").toString()));
			
		    
		}
		
		// 총 건수 넣기
		String allReportVal = "총 건수 : " + allReport +"건";
		subTitle1.createCell(2).setCellValue(allReportVal);
		
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
    
    
    // 주소라벨 출력
    public void addDownload(Map model, HSSFWorkbook wb) {
    	String pickLabelT = (String) model.get("labelType"); 
    	int labelType = Integer.parseInt(pickLabelT); // 라벨 타입 가져오기 16 or 24
    	
    	// 엑셀에서 사용할 데이터 리스트 가져오기
    	List dataList = (List) model.get("dataList");
    	
    	int dataCnt = dataList.size(); // 받아온 데이터 전체 수
    	/*int writeCnt = 0; // 데이터 넣은 카운터
*/    	int dataCnt16; //sheet를 위한 변수 (16라벨)
    	int dataCnt24; //sheet를 위한 변수 (24라벨)
    	
    	int rowCnt = 0;
    	
    	// 16라벨일 때 ( 2 * 8 )
    	if(labelType == 16) {
    		
    		// 전체 sheet 수 구하기
    		if(dataCnt < 16) { // 받아온 데이터가 16개가 안될 때 
    			dataCnt16 = 1; 
    		} else { // 받아온 데이터가 16개 이상일 때
    			dataCnt16 = dataCnt/16 ;
    			int dataCnt16Chk = dataCnt%16; // 나머지 구하기 
    			
    			if(dataCnt16Chk > 0) { //나머지가 있다면
    				dataCnt16 = dataCnt16 + 1; // sheet 수 1개 더 추가
    			}
    		}
            
    		
            // 엑셀 생성하기
    		for(int i = 0; i < dataCnt16 ; i++) { // sheet 생성 for문
    			HSSFSheet sheet = wb.createSheet("label(" + (i+1) + ")"); // label(n) 형식으로 시트 생성  
    			
    			int cellRowCnt = (i * 8) * 2;
                for(int j = 0 ; j < 8; j++) {
                	for(int x = 0; x < 1; x++) {
                		HSSFRow headrow = sheet.createRow(rowCnt); // row 생성, 주소 입력
                		HSSFRow headrow1 = sheet.createRow(rowCnt + 1); // row 생성, 이름 입력
                		HSSFRow headrow2 = sheet.createRow(rowCnt + 2); // row 생성, 전화번호 및 우편번호 입력
                		
                		/*InfrmVO record = (InfrmVO) dataList.get(cellRowCnt);*/ // 라벨 row의 1번 라벨
                		if(cellRowCnt < dataCnt) {
                			Object pickRecord = dataList.get(cellRowCnt);
                    		InfrmVO record = (InfrmVO) pickRecord;
                    		
                    		// 폰트 및 스타일 설정
                    	    CellStyle cellStyle = wb.createCellStyle();
                    	    CellStyle cellStyle1 = wb.createCellStyle();
                    	    CellStyle cellStyle2 = wb.createCellStyle();
                    	    CellStyle cellStyle3 = wb.createCellStyle();
                    	    CellStyle cellStyle4 = wb.createCellStyle();
                    	    
                    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 가운데 정렬 (가로 기준)
                    	    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	  
                    	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    Font font = wb.createFont();
                    	    font.setFontHeightInPoints((short) 12); // 폰트 크기 12px로 설정
                    	    cellStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용

                    	    
                    	    Font font1 = wb.createFont();
                    	    font1.setFontHeightInPoints((short) 14);
                    	    font1.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle1.setFont(font1); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    Font font2 = wb.createFont();
                    	    font2.setFontHeightInPoints((short) 10);
                    	    cellStyle2.setFont(font2); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    
                    	    Font font3 = wb.createFont();
                    	    font3.setFontHeightInPoints((short) 11);
                    	    font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle3.setFont(font3); 

                    	    Font font4 = wb.createFont();
                    	    font4.setFontHeightInPoints((short) 12);
                    	    font4.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle4.setFont(font4); // 폰트 스타일을 셀 스타일에 적용
                    	 
                    		
                    	 // 주소 셀 0 (headrow) 생성 및 스타일 적용
                    	    HSSFCell cell0 = headrow.createCell(0);
                    	    cell0.setCellValue("none".equals(record.getAddressHome()) ? "" : record.getAddressHome()); // 주소 입력
                    	    cell0.setCellStyle(cellStyle); // 스타일 적용
                    	    sheet.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, 1)); // 주소 입력 란 셀 병합

                    	    // 이름 셀 0 (headrow1) 생성 및 스타일 적용
                    	    HSSFCell cell1 = headrow1.createCell(0);
                    	    cell1.setCellValue("none".equals(record.getInformerName()) ? "" : record.getInformerName()); // 이름 입력
                    	    cell1.setCellStyle(cellStyle1); // 스타일 적용

                    	    // 통신원 셀 1 (headrow1) 생성
                    	    HSSFCell cell2 = headrow1.createCell(1);
                    	    cell2.setCellValue("통신원님 귀하");
                    	    cell2.setCellStyle(cellStyle2); // 스타일 적용

                    	    // 전화 번호 셀 0 (headrow2) 생성 및 스타일 적용
                    	    HSSFCell cell3 = headrow2.createCell(0);
                    	    cell3.setCellValue("none".equals(record.getPhoneCell()) ? "" : record.getPhoneCell()); // 전화 번호 입력
                    	    cell3.setCellStyle(cellStyle3); // 스타일 적용

                    	    // 우편번호 셀 1 (headrow2) 생성
                    	    HSSFCell cell4 = headrow2.createCell(1);
                    	    cell4.setCellValue("none".equals(record.getZipcode()) ? "" : record.getZipcode()); // 우편번호 입력
                    	    cell4.setCellStyle(cellStyle4); // 스타일 적용

                    	    // 시트 설정
                    	    sheet.setColumnWidth(0, 5700);
                    	    sheet.setColumnWidth(1, 5700);
                    	    headrow.setHeightInPoints(47.33f); // row height
                    	    
                    	    

                		} else {
                			break;
                		}
                		
                		sheet.setColumnWidth(2, 700);
                		
                		
                		if((cellRowCnt+1) < dataCnt){
                			Object pickRecord2 = dataList.get(cellRowCnt+1);
                    		InfrmVO record2 = (InfrmVO) pickRecord2;
                    		
                    		// 폰트 및 스타일 설정
                    	    CellStyle cellStyle = wb.createCellStyle();
                    	    CellStyle cellStyle1 = wb.createCellStyle();
                    	    CellStyle cellStyle2 = wb.createCellStyle();
                    	    CellStyle cellStyle3 = wb.createCellStyle();
                    	    CellStyle cellStyle4 = wb.createCellStyle();
                    	    
                    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 가운데 정렬 (가로 기준)
                    	    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	  
                    	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    Font font = wb.createFont();
                    	    font.setFontHeightInPoints((short) 12); // 폰트 크기 12px로 설정
                    	    cellStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용

                    	    
                    	    Font font1 = wb.createFont();
                    	    font1.setFontHeightInPoints((short) 14);
                    	    font1.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle1.setFont(font1); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    Font font2 = wb.createFont();
                    	    font2.setFontHeightInPoints((short) 10);
                    	    cellStyle2.setFont(font2); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    
                    	    Font font3 = wb.createFont();
                    	    font3.setFontHeightInPoints((short) 11);
                    	    font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle3.setFont(font3); 

                    	    Font font4 = wb.createFont();
                    	    font4.setFontHeightInPoints((short) 12);
                    	    font4.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle4.setFont(font4); // 폰트 스타일을 셀 스타일에 적용
                    	 
                    		
                    	 // 주소 셀 0 (headrow) 생성 및 스타일 적용
                    	    HSSFCell cell0 = headrow.createCell(3);
                    	    cell0.setCellValue("none".equals(record2.getAddressHome()) ? "" : record2.getAddressHome()); // 주소 입력
                    	    cell0.setCellStyle(cellStyle); // 스타일 적용
                    	    sheet.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 3, 4)); // 주소 입력 란 셀 병합

                    	    // 이름 셀 0 (headrow1) 생성 및 스타일 적용
                    	    HSSFCell cell1 = headrow1.createCell(3);
                    	    cell1.setCellValue("none".equals(record2.getInformerName()) ? "" : record2.getInformerName()); // 이름 입력
                    	    cell1.setCellStyle(cellStyle1); // 스타일 적용

                    	    // 통신원 셀 1 (headrow1) 생성
                    	    HSSFCell cell2 = headrow1.createCell(4);
                    	    cell2.setCellValue("통신원님 귀하");
                    	    cell2.setCellStyle(cellStyle2); // 스타일 적용

                    	    // 전화 번호 셀 0 (headrow2) 생성 및 스타일 적용
                    	    HSSFCell cell3 = headrow2.createCell(3);
                    	    cell3.setCellValue("none".equals(record2.getPhoneCell()) ? "" : record2.getPhoneCell()); // 전화 번호 입력
                    	    cell3.setCellStyle(cellStyle3); // 스타일 적용

                    	    // 우편번호 셀 1 (headrow2) 생성
                    	    HSSFCell cell4 = headrow2.createCell(4);
                    	    cell4.setCellValue("none".equals(record2.getZipcode()) ? "" : record2.getZipcode()); // 우편번호 입력
                    	    cell4.setCellStyle(cellStyle4); // 스타일 적용
                    		
                    		// 시트 설정
                    		sheet.setColumnWidth(3, 5700);
                    		sheet.setColumnWidth(4, 5700);
                    		headrow.setHeightInPoints(47.33f); // row height
                		} else {
                			break;
                		}
              		
                	}
            		
                	rowCnt = rowCnt + 3;
                	
                	cellRowCnt = cellRowCnt + 2;
                	
                }
                
                // 값 초기화
                rowCnt = 0;
/*                dataCnt = dataCnt-16;*/
    		}
            

    	} else { // 24라벨일 때 ( 3 * 8 )
    		
    		// 전체 sheet 수 구하기
    		if(dataCnt < 24) { // 받아온 데이터가 16개가 안될 때 
    			dataCnt24 = 1; 
    		} else { // 받아온 데이터가 16개 이상일 때
    			dataCnt24 = dataCnt/24 ;
    			int dataCnt24Chk = dataCnt % 24; // 나머지 구하기 
    			
    			if(dataCnt24Chk > 0) { //나머지가 있다면
    				dataCnt24 = dataCnt24 + 1; // sheet 수 1개 더 추가
    			}
    		}
    		
    		
    		// 엑셀 생성하기
    		for(int i = 0; i < dataCnt24 ; i++) { // sheet 생성 for문
    			HSSFSheet sheet = wb.createSheet("label(" + (i+1) + ")"); // label(n) 형식으로 시트 생성  
    			
    			int cellRowCnt = (i * 12) * 2;
                for(int j = 0 ; j < 8; j++) {
                	for(int x = 0; x < 1; x++) {
                		HSSFRow headrow = sheet.createRow(rowCnt); // row 생성, 주소 입력
                		HSSFRow headrow1 = sheet.createRow(rowCnt + 1); // row 생성, 이름 입력
                		HSSFRow headrow2 = sheet.createRow(rowCnt + 2); // row 생성, 전화번호 및 우편번호 입력
                		
                		/*InfrmVO record = (InfrmVO) dataList.get(cellRowCnt);*/ // 라벨 row의 1번 라벨
                		if(cellRowCnt < dataCnt) {
                			Object pickRecord = dataList.get(cellRowCnt);
                    		InfrmVO record = (InfrmVO) pickRecord;

                    		// 폰트 및 스타일 설정
                    	    CellStyle cellStyle = wb.createCellStyle();
                    	    CellStyle cellStyle1 = wb.createCellStyle();
                    	    CellStyle cellStyle2 = wb.createCellStyle();
                    	    CellStyle cellStyle3 = wb.createCellStyle();
                    	    CellStyle cellStyle4 = wb.createCellStyle();
                    	    
                    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 가운데 정렬 (가로 기준)
                    	    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	  
                    	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    Font font = wb.createFont();
                    	    font.setFontHeightInPoints((short) 12); // 폰트 크기 12px로 설정
                    	    cellStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용

                    	    
                    	    Font font1 = wb.createFont();
                    	    font1.setFontHeightInPoints((short) 14);
                    	    font1.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle1.setFont(font1); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    Font font2 = wb.createFont();
                    	    font2.setFontHeightInPoints((short) 10);
                    	    cellStyle2.setFont(font2); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    
                    	    Font font3 = wb.createFont();
                    	    font3.setFontHeightInPoints((short) 11);
                    	    font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle3.setFont(font3); 

                    	    Font font4 = wb.createFont();
                    	    font4.setFontHeightInPoints((short) 12);
                    	    font4.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle4.setFont(font4); // 폰트 스타일을 셀 스타일에 적용
                    	 
                    		
                    	 // 주소 셀 0 (headrow) 생성 및 스타일 적용
                    	    HSSFCell cell0 = headrow.createCell(0);
                    	    cell0.setCellValue("none".equals(record.getAddressHome()) ? "" : record.getAddressHome()); // 주소 입력
                    	    cell0.setCellStyle(cellStyle); // 스타일 적용
                    	    sheet.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 0, 1)); // 주소 입력 란 셀 병합

                    	    // 이름 셀 0 (headrow1) 생성 및 스타일 적용
                    	    HSSFCell cell1 = headrow1.createCell(0);
                    	    cell1.setCellValue("none".equals(record.getInformerName()) ? "" : record.getInformerName()); // 이름 입력
                    	    cell1.setCellStyle(cellStyle1); // 스타일 적용

                    	    // 통신원 셀 1 (headrow1) 생성
                    	    HSSFCell cell2 = headrow1.createCell(1);
                    	    cell2.setCellValue("통신원님 귀하");
                    	    cell2.setCellStyle(cellStyle2); // 스타일 적용

                    	    // 전화 번호 셀 0 (headrow2) 생성 및 스타일 적용
                    	    HSSFCell cell3 = headrow2.createCell(0);
                    	    cell3.setCellValue("none".equals(record.getPhoneCell()) ? "" : record.getPhoneCell()); // 전화 번호 입력
                    	    cell3.setCellStyle(cellStyle3); // 스타일 적용

                    	    // 우편번호 셀 1 (headrow2) 생성
                    	    HSSFCell cell4 = headrow2.createCell(1);
                    	    cell4.setCellValue("none".equals(record.getZipcode()) ? "" : record.getZipcode()); // 우편번호 입력
                    	    cell4.setCellStyle(cellStyle4); // 스타일 적용

                    	    // 시트 설정
                    	    sheet.setColumnWidth(0, 4000);
                    	    sheet.setColumnWidth(1, 4000);

                    	    headrow.setHeightInPoints(47.33f); // 행 높이 설정
                		} else {
                			break;
                		}
                		
                		sheet.setColumnWidth(2, 500);
                		
                		if((cellRowCnt+1) < dataCnt){
                			Object pickRecord2 = dataList.get(cellRowCnt+1);
                    		InfrmVO record2 = (InfrmVO) pickRecord2;
                    		
                    		// 폰트 및 스타일 설정
                    	    CellStyle cellStyle = wb.createCellStyle();
                    	    CellStyle cellStyle1 = wb.createCellStyle();
                    	    CellStyle cellStyle2 = wb.createCellStyle();
                    	    CellStyle cellStyle3 = wb.createCellStyle();
                    	    CellStyle cellStyle4 = wb.createCellStyle();
                    	    
                    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 가운데 정렬 (가로 기준)
                    	    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	  
                    	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    Font font = wb.createFont();
                    	    font.setFontHeightInPoints((short) 12); // 폰트 크기 12px로 설정
                    	    cellStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용

                    	    
                    	    Font font1 = wb.createFont();
                    	    font1.setFontHeightInPoints((short) 14);
                    	    font1.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle1.setFont(font1); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    Font font2 = wb.createFont();
                    	    font2.setFontHeightInPoints((short) 10);
                    	    cellStyle2.setFont(font2); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    
                    	    Font font3 = wb.createFont();
                    	    font3.setFontHeightInPoints((short) 11);
                    	    font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle3.setFont(font3); 

                    	    Font font4 = wb.createFont();
                    	    font4.setFontHeightInPoints((short) 12);
                    	    font4.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle4.setFont(font4); // 폰트 스타일을 셀 스타일에 적용
                    	 
                    		
                    	 // 주소 셀 0 (headrow) 생성 및 스타일 적용
                    	    HSSFCell cell0 = headrow.createCell(3);
                    	    cell0.setCellValue("none".equals(record2.getAddressHome()) ? "" : record2.getAddressHome()); // 주소 입력
                    	    cell0.setCellStyle(cellStyle); // 스타일 적용
                    	    sheet.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 3, 4)); // 주소 입력 란 셀 병합

                    	    // 이름 셀 0 (headrow1) 생성 및 스타일 적용
                    	    HSSFCell cell1 = headrow1.createCell(3);
                    	    cell1.setCellValue("none".equals(record2.getInformerName()) ? "" : record2.getInformerName()); // 이름 입력
                    	    cell1.setCellStyle(cellStyle1); // 스타일 적용

                    	    // 통신원 셀 1 (headrow1) 생성
                    	    HSSFCell cell2 = headrow1.createCell(4);
                    	    cell2.setCellValue("통신원님 귀하");
                    	    cell2.setCellStyle(cellStyle2); // 스타일 적용

                    	    // 전화 번호 셀 0 (headrow2) 생성 및 스타일 적용
                    	    HSSFCell cell3 = headrow2.createCell(3);
                    	    cell3.setCellValue("none".equals(record2.getPhoneCell()) ? "" : record2.getPhoneCell()); // 전화 번호 입력
                    	    cell3.setCellStyle(cellStyle3); // 스타일 적용

                    	    // 우편번호 셀 1 (headrow2) 생성
                    	    HSSFCell cell4 = headrow2.createCell(4);
                    	    cell4.setCellValue("none".equals(record2.getZipcode()) ? "" : record2.getZipcode()); // 우편번호 입력
                    	    cell4.setCellStyle(cellStyle4); // 스타일 적용

                    	    // 시트 설정
                    	    sheet.setColumnWidth(3, 4000);
                    	    sheet.setColumnWidth(4, 4000);

                    	    headrow.setHeightInPoints(47.33f); // 행 높이 설정
                		} else {
                			break;
                		}
                		
                		sheet.setColumnWidth(5, 500);
                		
                		if((cellRowCnt+2) < dataCnt){
                			Object pickRecord3 = dataList.get(cellRowCnt+2);
                    		InfrmVO record3 = (InfrmVO) pickRecord3;
                    		
                    		// 폰트 및 스타일 설정
                    	    CellStyle cellStyle = wb.createCellStyle();
                    	    CellStyle cellStyle1 = wb.createCellStyle();
                    	    CellStyle cellStyle2 = wb.createCellStyle();
                    	    CellStyle cellStyle3 = wb.createCellStyle();
                    	    CellStyle cellStyle4 = wb.createCellStyle();
                    	    
                    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 가운데 정렬 (가로 기준)
                    	    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 가운데 정렬 (가로 기준)
                    	    cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	  
                    	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
                    	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
                    	    
                    	    Font font = wb.createFont();
                    	    font.setFontHeightInPoints((short) 12); // 폰트 크기 12px로 설정
                    	    cellStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용

                    	    
                    	    Font font1 = wb.createFont();
                    	    font1.setFontHeightInPoints((short) 14);
                    	    font1.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle1.setFont(font1); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    Font font2 = wb.createFont();
                    	    font2.setFontHeightInPoints((short) 10);
                    	    cellStyle2.setFont(font2); // 폰트 스타일을 셀 스타일에 적용
                    	    
                    	    
                    	    Font font3 = wb.createFont();
                    	    font3.setFontHeightInPoints((short) 11);
                    	    font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle3.setFont(font3); 

                    	    Font font4 = wb.createFont();
                    	    font4.setFontHeightInPoints((short) 12);
                    	    font4.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
                    	    cellStyle4.setFont(font4); // 폰트 스타일을 셀 스타일에 적용
                    	 
                    		
                    	 // 주소 셀 0 (headrow) 생성 및 스타일 적용
                    	    HSSFCell cell0 = headrow.createCell(6);
                    	    cell0.setCellValue("none".equals(record3.getAddressHome()) ? "" : record3.getAddressHome()); // 주소 입력
                    	    cell0.setCellStyle(cellStyle); // 스타일 적용
                    	    sheet.addMergedRegion(new CellRangeAddress(rowCnt, rowCnt, 6, 7)); // 주소 입력 란 셀 병합

                    	    // 이름 셀 0 (headrow1) 생성 및 스타일 적용
                    	    HSSFCell cell1 = headrow1.createCell(6);
                    	    cell1.setCellValue("none".equals(record3.getInformerName()) ? "" : record3.getInformerName()); // 이름 입력
                    	    cell1.setCellStyle(cellStyle1); // 스타일 적용

                    	    // 통신원 셀 1 (headrow1) 생성
                    	    HSSFCell cell2 = headrow1.createCell(7);
                    	    cell2.setCellValue("통신원님 귀하");
                    	    cell2.setCellStyle(cellStyle2); // 스타일 적용

                    	    // 전화 번호 셀 0 (headrow2) 생성 및 스타일 적용
                    	    HSSFCell cell3 = headrow2.createCell(6);
                    	    cell3.setCellValue("none".equals(record3.getPhoneCell()) ? "" : record3.getPhoneCell()); // 전화 번호 입력
                    	    cell3.setCellStyle(cellStyle3); // 스타일 적용

                    	    // 우편번호 셀 1 (headrow2) 생성
                    	    HSSFCell cell4 = headrow2.createCell(7);
                    	    cell4.setCellValue("none".equals(record3.getZipcode()) ? "" : record3.getZipcode()); // 우편번호 입력
                    	    cell4.setCellStyle(cellStyle4); // 스타일 적용

                    	    // 시트 설정
                    	    sheet.setColumnWidth(6, 4000);
                    	    sheet.setColumnWidth(7, 4000);

                    	    headrow.setHeightInPoints(47.33f); // 행 높이 설정
                		} else {
                			break;
                		}
                		
              		
                	}
            		
                	rowCnt = rowCnt + 3;
                	
                	cellRowCnt = cellRowCnt + 3;
                	
                }
                
                // 값 초기화
                rowCnt = 0;
    		}
    		
    	}
    }
    
    
    
    // 시간대별 교통정보 제공대장
    public void standardInformerTypeTime(Map model, HSSFWorkbook wb) {
    	List timeDataList = (List) model.get("timeData"); // 시간별 데이터
    	List ifrmDataList = (List) model.get("ifrmData"); // 제보자별 데이터
    	List rptTataList = (List) model.get("rptTData"); // 제보유형별 데이터
    	List rptMDataList = (List) model.get("rptMData"); // 제보수단별 데이터
    	
    	// 전체 건수 구하기
    	int allSum = 0;
    	
    	// sheet 생성 및 이름 저장
    	HSSFSheet sheet = wb.createSheet("시간대별 교통정보 제공대장");
         
        // title style 변경 
        // style 객체 생성
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬 (가로 기준)
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
 	    
 	    Font font = wb.createFont(); // 폰트 객체 생성
 	    font.setFontHeightInPoints((short) 30); // 폰트 크기 설정
 	    font.setColor(IndexedColors.BLUE.getIndex()); // 폰트 색상 설정 => 예제 파일 기준으로 파란색 설정
 	    font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드체로 설정
 	    font.setFontName("굴림체");
 	    titleStyle.setFont(font); // 폰트 스타일을 셀 스타일에 적용
 	    
 	    
 	    // 엑셀 title 넣을 행 생성 (A1)
    	HSSFRow titlerow = sheet.createRow(0);
        
        HSSFCell titleCell = titlerow.createCell(0); // 셀 생성
        
        // CellRangeAddress(a,b,c,d) 사용법
        // => CellRangeAddress(첫 행,마지막 행,첫 열,마지막 열)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16)); // title cell 병합 / 예제 파일 기준으로 17칸 병합
        
        titleCell.setCellValue(model.get("titleName").toString()); // title row에 title 삽입
        titleCell.setCellStyle(titleStyle); // 스타일 적용
        
 	    // cell width ,  height 변경
 	    sheet.setColumnWidth(0, 5700); // setColumnWidth(변경 할 cell , 크기)
 	    titlerow.setHeightInPoints(47.33f); //setHeightInPoints 크기)
 	    
 	    // 엑셀 대제목 끝 ========================================================= //
 	    
        // title style 변경 
        // style 객체 생성
        CellStyle dateStyle = wb.createCellStyle();
        dateStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 오른쪽 정렬 (가로 기준)
        dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
 	    
 	    Font dateFont = wb.createFont(); // 폰트 객체 생성
 	    dateFont.setFontHeightInPoints((short) 16); // 폰트 크기 설정
 	    dateFont.setColor(IndexedColors.BLUE.getIndex()); // 폰트 색상 설정 => 예제 파일 기준으로 파란색 설정
 	    dateFont.setFontName("굴림체");
 	    dateStyle.setFont(dateFont); // 폰트 스타일을 셀 스타일에 적용
 	   
    	// 시작일, 종료일 YYYY년 DD월 MM일 ~  YYYY년 DD월 MM일 형식으로 쪼개기      
    	// 쪼개기 위해 시작일과 종료일 받아오기
    	String startDate = model.get("start_date").toString();
    	String endDate = model.get("end_date").toString();
    	
    	String sDate = ( startDate.substring(0, 4) + "년 " + startDate.substring(4, 6) +"월 " + startDate.substring(6, 8) + "일 ~ ");
    	String eDate = ( endDate.substring(0, 4) + "년 " + endDate.substring(4, 6) +"월 " + endDate.substring(6, 8) + "일" );
    	
    	String dateStr = sDate + eDate; // 최종 반환 String
    	
    	
 	    // 기간 넣을 행 생성 (A2)
    	HSSFRow daterow = sheet.createRow(1);	
    	HSSFCell dateCell = daterow.createCell(0);
    	
    	sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 16)); // title cell 병합 / 예제 파일 기준으로 17칸 병합
    	dateCell.setCellValue(dateStr); // title row에 title 삽입
    	dateCell.setCellStyle(dateStyle); // 스타일 적용
    	
    	// cell width ,  height 변경
 	    sheet.setColumnWidth(0, 5700); // setColumnWidth(변경 할 cell , 크기)
 	    daterow.setHeightInPoints(27.88f); //setHeightInPoints 크기)
    	
    	
 	    // 기간 끝 ========================================================= //
 	    // 시간 배열
 	    
	 	CellStyle dayTypehead = wb.createCellStyle();
	 	dayTypehead.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 오른쪽 정렬 (가로 기준)
	 	dayTypehead.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
	 	dayTypehead.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // 배경색 설정
	 	dayTypehead.setFillPattern(CellStyle.SOLID_FOREGROUND);  // 배경색이 채워지도록 패턴 설정
	 	dayTypehead.setBorderRight(HSSFCellStyle.BORDER_THIN); // 테두리 설정
	 	dayTypehead.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 	dayTypehead.setBorderTop(HSSFCellStyle.BORDER_THIN);
	 	dayTypehead.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	 	
	 	Font dayTypeF = wb.createFont(); // 폰트 객체 생성
	 	dayTypeF.setFontHeightInPoints((short) 12); // 폰트 크기 설정
	 	dayTypeF.setFontName("굴림체");
	 	dayTypehead.setFont(dayTypeF); // 폰트 스타일을 셀 스타일에 적용
	 	
	 	
	 	// 구분 , 건수,비율,캐스터,스튜디오 등등의 > 스타일
	 		CellStyle gubun = wb.createCellStyle();
	 	   gubun.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 오른쪽 정렬 (가로 기준)
	 	   gubun.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
	 	   /*gubun.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // 배경색 설정
	*/ 	   /*gubun.setFillPattern(CellStyle.SOLID_FOREGROUND);*/  // 배경색이 채워지도록 패턴 설정
	 	   gubun.setBorderRight(HSSFCellStyle.BORDER_THIN); // 테두리 설정
	 	   gubun.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 	   gubun.setBorderTop(HSSFCellStyle.BORDER_THIN);
	 	   gubun.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 	
		 	Font gubunF = wb.createFont(); // 폰트 객체 생성
		 	gubunF.setFontHeightInPoints((short) 11); // 폰트 크기 설정
		 	gubunF.setFontName("굴림체");
		 	gubun.setFont(gubunF); // 폰트 스타일을 셀 스타일에 적용
		 	
		// 오전, 오후 표 안 데이터 스타일
		 	CellStyle dataStyle = wb.createCellStyle();  
		 	dataStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); 
		 	dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); 
		 	dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		 	dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 	dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 	dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 	
		Font dataStyleF = wb.createFont(); // 폰트 객체 생성
		dataStyleF.setFontHeightInPoints((short) 11); // 폰트 크기 설정
		dataStyleF.setFontName("굴림체");
		dataStyle.setFont(dataStyleF); // 폰트 스타일을 셀 스타일에 적용 	
		 	
		 	
		 	
		// 총 접수 건수, 자체 처리, 방송요청
		CellStyle allStyle = wb.createCellStyle();
		allStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 오른쪽 정렬 (가로 기준)
		allStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
		allStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex()); // 배경색 설정*/ 	   
		allStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);  // 배경색이 채워지도록 패턴 설정
		allStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 테두리 설정
		allStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		allStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		allStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 	
		Font allStyleF = wb.createFont(); // 폰트 객체 생성
		allStyleF.setFontHeightInPoints((short) 11); // 폰트 크기 설정
		allStyleF.setFontName("굴림체");
	 	allStyle.setFont(allStyleF); // 폰트 스타일을 셀 스타일에 적용 	
		
		// 방송, 비방송, 주요제보 등
		CellStyle otherStyle = wb.createCellStyle();
		otherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 오른쪽 정렬 (가로 기준)
		otherStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
		otherStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex()); // 배경색 설정*/ 	   
		otherStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);  // 배경색이 채워지도록 패턴 설정
		otherStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 테두리 설정
		otherStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		otherStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		otherStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		Font otherStyleF = wb.createFont(); // 폰트 객체 생성
		otherStyleF.setFontHeightInPoints((short) 11); // 폰트 크기 설정
		otherStyleF.setFontName("굴림체");
		otherStyle.setFont(otherStyleF); // 폰트 스타일을 셀 스타일에 적용 	
		
	 	
 	    String[] daytimeArr = new String[28];
 	    daytimeArr[0] = "계";
 	    daytimeArr[1] = "오전";
 	    daytimeArr[2] = "00-01";
	 	daytimeArr[3] = "01-02";	
	 	daytimeArr[4] = "02-03";
	 	daytimeArr[5] = "03-04";
	 	daytimeArr[6] = "04-05";
	 	daytimeArr[7] = "05-06";
	 	daytimeArr[8] = "06-07";
	 	daytimeArr[9] = "07-08";
	 	daytimeArr[10] = "08-09";
	 	daytimeArr[11] = "09-10";
	 	daytimeArr[12] = "10-11";
	 	daytimeArr[13] = "11-12";
	 	daytimeArr[14] = "계";
	 	daytimeArr[15] = "오후";
	 	daytimeArr[16] = "12-13";
	 	daytimeArr[17] = "13-14";
	 	daytimeArr[18] = "14-15";
	 	daytimeArr[19] = "15-16";
	 	daytimeArr[20] = "16-17";
	 	daytimeArr[21] = "17-18";
	 	daytimeArr[22] = "18-19";
	 	daytimeArr[23] = "19-20";
	 	daytimeArr[24] = "20-21";
	 	daytimeArr[25] = "21-22";
	 	daytimeArr[26] = "22-23";
	 	daytimeArr[27] = "23-24";
 	    
	 	HSSFRow dayTyperow = sheet.createRow(5);  // 오전 구분 (A6)
	 	HSSFRow dayallrow = sheet.createRow(6); // 오전 총 접수 건수
	 	
	 	HSSFRow nightTyperow = sheet.createRow(28);  // 오후 구분 (A29)
	 	HSSFRow nightallrow = sheet.createRow(29); // 오후 총 접수 건수

	 	HSSFCell dayTypeCell = dayTyperow.createCell(0); // 구분(오전)
	 	HSSFCell dayallCell = dayallrow.createCell(0); // 오전 총 접수 건수
 	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 2)); // cell 병합 / 예제 파일 기준으로3칸 병합
 	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 2)); // cell 병합 / 예제 파일 기준으로3칸 병합
 	    dayTypeCell.setCellValue("구분"); // title row에 title 삽입
 	    dayallCell.setCellValue("총 접수 건수"); // title row에 title 삽입
 	    dayTypeCell.setCellStyle(gubun);
 	    dayTyperow.createCell(1).setCellStyle(gubun);
 	    dayTyperow.createCell(2).setCellStyle(gubun);
 	    dayallCell.setCellStyle(allStyle);
 	    dayallrow.createCell(1).setCellStyle(allStyle);
 	 	dayallrow.createCell(2).setCellStyle(allStyle);

 	    HSSFCell nightTypeCell = nightTyperow.createCell(0); // 구분(오후)
 	    HSSFCell nightallCell = nightallrow.createCell(0); // 오전 총 접수 건수
 	    sheet.addMergedRegion(new CellRangeAddress(28, 28, 0, 2)); // cell 병합 / 예제 파일 기준으로3칸 병합
 	    sheet.addMergedRegion(new CellRangeAddress(29, 29, 0, 2)); // cell 병합 / 예제 파일 기준으로3칸 병합
 	    nightallCell.setCellValue("총 접수 건수"); // title row에 title 삽입
 	    nightallCell.setCellStyle(allStyle);
 	    nightallrow.createCell(1).setCellStyle(allStyle);
 	    nightallrow.createCell(2).setCellStyle(allStyle);
 	    
 	    // 자체 처리 / 방송 건수
 	    HSSFRow daysendcnt = sheet.createRow(7); 
 	    HSSFRow daysendper = sheet.createRow(8);  
 	    HSSFRow daybrodcnt = sheet.createRow(9);  
 	    HSSFRow daybrodper = sheet.createRow(10);

 	   HSSFCell daysendcntcell = daysendcnt.createCell(0); 
 	   sheet.addMergedRegion(new CellRangeAddress(7, 8, 0, 1)); // cell 병합 
 	   daysendcntcell.setCellValue("자체 처리");
 	   daysendcntcell.setCellStyle(allStyle);
 	   daysendcnt.createCell(1).setCellStyle(allStyle);
 	   daysendper.createCell(0).setCellStyle(allStyle);
 	   daysendper.createCell(1).setCellStyle(allStyle);
 	   
 	   HSSFCell daysendcnttitle = daysendcnt.createCell(2);
 	   daysendcnttitle.setCellValue("건수");
 	   daysendcnttitle.setCellStyle(gubun);
 	  
	   HSSFCell daysendpertitle = daysendper.createCell(2);
	   daysendpertitle.setCellValue("비율"); 
	   daysendpertitle.setCellStyle(gubun);
	   
	   HSSFCell daybrodcntcell = daybrodcnt.createCell(0); 
 	   sheet.addMergedRegion(new CellRangeAddress(9, 10, 0, 1)); // cell 병합 
 	   daybrodcntcell.setCellValue("방송 요청");
 	   daybrodcntcell.setCellStyle(allStyle);
 	   daybrodcnt.createCell(1).setCellStyle(allStyle);
 	   daybrodper.createCell(0).setCellStyle(allStyle);
 	   daybrodper.createCell(1).setCellStyle(allStyle);
 	   
 	   HSSFCell daybrodcnttitle = daybrodcnt.createCell(2);
 	   daybrodcnttitle.setCellValue("건수");
 	   daybrodcnttitle.setCellStyle(gubun);
 	  
	   HSSFCell daybrodpertitle = daybrodper.createCell(2);
	   daybrodpertitle.setCellValue("비율");  
	   daybrodpertitle.setCellStyle(gubun);
	   
 	
 	    HSSFRow nightsendcnt = sheet.createRow(30); 
	    HSSFRow nightsendper = sheet.createRow(31);  
	    HSSFRow nightbrodcnt = sheet.createRow(32);  
	    HSSFRow nightbrodper = sheet.createRow(33);
	    
	    HSSFCell nightsendcntcell = nightsendcnt.createCell(0); 
	 	sheet.addMergedRegion(new CellRangeAddress(30, 31, 0, 1)); // cell 병합 
	 	nightsendcntcell.setCellValue("자체 처리");  
	 	nightsendcntcell.setCellStyle(allStyle);
	 	nightsendcnt.createCell(1).setCellStyle(allStyle);
	 	
	 	HSSFCell nightsendcnttitle = nightsendcnt.createCell(2);
	 	nightsendcnttitle.setCellValue("건수");
	 	nightsendcnttitle.setCellStyle(gubun);
	 	
		HSSFCell nightsendpertitle = nightsendper.createCell(2);
		nightsendpertitle.setCellValue("비율"); 
		nightsendpertitle.setCellStyle(gubun);
		
		HSSFCell nightbrodcntcell = nightbrodcnt.createCell(0); 
	 	sheet.addMergedRegion(new CellRangeAddress(32, 33, 0, 1)); // cell 병합 
	 	nightbrodcntcell.setCellValue("방송 요청");
	 	nightbrodcntcell.setCellStyle(allStyle);
	 	nightbrodcnt.createCell(1).setCellStyle(allStyle);
	 	nightbrodper.createCell(0).setCellStyle(allStyle);
	 	nightbrodper.createCell(1).setCellStyle(allStyle);
	 	
	 	HSSFCell nightbrodcnttitle = nightbrodcnt.createCell(2);
	 	nightbrodcnttitle.setCellValue("건수");
	 	nightbrodcnttitle.setCellStyle(gubun);
	 	
		HSSFCell nightbrodpertitle = nightbrodper.createCell(2);
		nightbrodpertitle.setCellValue("비율"); 
		nightbrodpertitle.setCellStyle(gubun);
		
		
		HSSFRow dayybroadcnt = sheet.createRow(12); 
 	    HSSFRow dayybroadper = sheet.createRow(13);  
 	    HSSFRow dayybroadstudio = sheet.createRow(14);  
 	    HSSFRow dayybroadcaster = sheet.createRow(15);
 	    HSSFRow daynbroadcnt = sheet.createRow(16);  
	    HSSFRow daynbroadper = sheet.createRow(17);
	    
	    sheet.addMergedRegion(new CellRangeAddress(12, 15, 1, 1));
	    sheet.addMergedRegion(new CellRangeAddress(16, 17, 1, 1));
	    
	    HSSFCell dayybroadcntcell = dayybroadcnt.createCell(1); 
	    HSSFCell daynbroadcntcell = daynbroadcnt.createCell(1); 
	    dayybroadcntcell.setCellValue("방송");
	    daynbroadcntcell.setCellValue("비방송");
	    dayybroadcntcell.setCellStyle(otherStyle);
	    daynbroadcntcell.setCellStyle(otherStyle);
	    
	    dayybroadper.createCell(1).setCellStyle(otherStyle);
	    dayybroadstudio.createCell(1).setCellStyle(otherStyle);
	    dayybroadcaster.createCell(1).setCellStyle(otherStyle);
	    daynbroadper.createCell(1).setCellStyle(otherStyle);
	    
	    HSSFCell dayybroadcnttitle = dayybroadcnt.createCell(2);
	    dayybroadcnttitle.setCellValue("건수");
	    dayybroadcnttitle.setCellStyle(gubun);
	    
		HSSFCell dayybroadpertitle = dayybroadper.createCell(2);
		dayybroadpertitle.setCellValue("비율"); 
		dayybroadpertitle.setCellStyle(gubun);
		
		HSSFCell dayybroadstudiotitle = dayybroadstudio.createCell(2);
		dayybroadstudiotitle.setCellValue("STUDIO");
		dayybroadstudiotitle.setCellStyle(gubun);
		
		HSSFCell dayybroadcastertitle = dayybroadcaster.createCell(2);
		dayybroadcastertitle.setCellValue("CASTER"); 
		dayybroadcastertitle.setCellStyle(gubun);
		
		HSSFCell daynbroadcnttitle = daynbroadcnt.createCell(2);
		daynbroadcnttitle.setCellValue("건수");
		daynbroadcnttitle.setCellStyle(gubun);
		
		HSSFCell daynbroadpertitle = daynbroadper.createCell(2);
		daynbroadpertitle.setCellValue("비율"); 
		daynbroadpertitle.setCellStyle(gubun);
	    
	    HSSFRow nightybroadcnt = sheet.createRow(35); 
 	    HSSFRow nightybroadper = sheet.createRow(36);  
 	    HSSFRow nightybroadstudio = sheet.createRow(37);  
 	    HSSFRow nightybroadcaster = sheet.createRow(38);
 	    HSSFRow nightnbroadcnt = sheet.createRow(39);  
	    HSSFRow nightnbroadper = sheet.createRow(40);
	    
	    sheet.addMergedRegion(new CellRangeAddress(35, 38, 1, 1));
	    sheet.addMergedRegion(new CellRangeAddress(39, 40, 1, 1));

	    HSSFCell nightybroadcntcell = nightybroadcnt.createCell(1); 
	    HSSFCell nightnbroadcntcell = nightnbroadcnt.createCell(1); 
	    nightybroadcntcell.setCellValue("방송");
	    nightnbroadcntcell.setCellValue("비방송");
	    nightybroadcntcell.setCellStyle(otherStyle);
	    nightnbroadcntcell.setCellStyle(otherStyle);
	    nightybroadcntcell.setCellStyle(otherStyle);
	    nightnbroadcntcell.setCellStyle(otherStyle);
	    
	    nightybroadper.createCell(1).setCellStyle(otherStyle);
	    nightybroadstudio.createCell(1).setCellStyle(otherStyle);
	    nightybroadcaster.createCell(1).setCellStyle(otherStyle);
	    nightnbroadper.createCell(1).setCellStyle(otherStyle);
	    
	    HSSFCell nightybroadcnttitle = nightybroadcnt.createCell(2);
	    nightybroadcnttitle.setCellValue("건수");
	    nightybroadcnttitle.setCellStyle(gubun);
	    
		HSSFCell nightybroadpertitle = nightybroadper.createCell(2);
		nightybroadpertitle.setCellValue("비율"); 
		nightybroadpertitle.setCellStyle(gubun);
		
		HSSFCell nightybroadstudiotitle = nightybroadstudio.createCell(2);
		nightybroadstudiotitle.setCellValue("STUDIO");
		nightybroadstudiotitle.setCellStyle(gubun);
		
		HSSFCell nightybroadcastertitle = nightybroadcaster.createCell(2);
		nightybroadcastertitle.setCellValue("CASTER"); 
		nightybroadcastertitle.setCellStyle(gubun);
		
		HSSFCell nightnbroadcnttitle = nightnbroadcnt.createCell(2);
		nightnbroadcnttitle.setCellValue("건수");
		nightnbroadcnttitle.setCellStyle(gubun);
		
		HSSFCell nightnbroadpertitle = nightnbroadper.createCell(2);
		nightnbroadpertitle.setCellValue("비율"); 
		nightnbroadpertitle.setCellStyle(gubun);
		
	    
	    HSSFRow dayicnt = sheet.createRow(19); 
 	    HSSFRow dayiybcnt = sheet.createRow(20);  
 	    HSSFRow dayiybroadper = sheet.createRow(21);  
 	    HSSFRow dayistudio = sheet.createRow(22);
 	    HSSFRow dayicaster = sheet.createRow(23);  

 	    HSSFRow nighticnt = sheet.createRow(42); 
	    HSSFRow nightiybcnt = sheet.createRow(43);  
	    HSSFRow nightiybroadper = sheet.createRow(44);  
	    HSSFRow nightistudio = sheet.createRow(45);
	    HSSFRow nighticaster = sheet.createRow(46); 
	    
	    sheet.addMergedRegion(new CellRangeAddress(19, 23, 1, 1));
	    sheet.addMergedRegion(new CellRangeAddress(42, 46, 1, 1));
	    
	    HSSFCell dayicntcell = dayicnt.createCell(1); 
	    HSSFCell nighticntcell = nighticnt.createCell(1); 
	    dayicntcell.setCellValue("주요 제보");
	    nighticntcell.setCellValue("주요 제보");
	    dayicntcell.setCellStyle(otherStyle);
	    nighticntcell.setCellStyle(otherStyle);
	    dayiybcnt.createCell(1).setCellStyle(otherStyle);
	    dayiybroadper.createCell(1).setCellStyle(otherStyle);
	    dayistudio.createCell(1).setCellStyle(otherStyle);
	    dayicaster.createCell(1).setCellStyle(otherStyle);
	    nightiybcnt.createCell(1).setCellStyle(otherStyle);
	    nightiybroadper.createCell(1).setCellStyle(otherStyle);
	    nightistudio.createCell(1).setCellStyle(otherStyle);
	    nighticaster.createCell(1).setCellStyle(otherStyle);
	    
	    HSSFCell dayicnttitle = dayicnt.createCell(2);
	    dayicnttitle.setCellValue("전송 건수");
	    dayicnttitle.setCellStyle(gubun);
	    
		HSSFCell dayiybcnttitle = dayiybcnt.createCell(2);
		dayiybcnttitle.setCellValue("방송 건수"); 
		dayiybcnttitle.setCellStyle(gubun);
		
		HSSFCell dayiybroadpertitle = dayiybroadper.createCell(2);
		dayiybroadpertitle.setCellValue("방송 비율");
		dayiybroadpertitle.setCellStyle(gubun);
		
		HSSFCell dayistudiotitle = dayistudio.createCell(2);
		dayistudiotitle.setCellValue("STUDIO"); 
		dayistudiotitle.setCellStyle(gubun);
		
		HSSFCell dayicastertitle = dayicaster.createCell(2);
		dayicastertitle.setCellValue("CASTER");
		dayicastertitle.setCellStyle(gubun);

		
		HSSFCell nighticnttitle = nighticnt.createCell(2);
		nighticnttitle.setCellValue("전송 건수");
		nighticnttitle.setCellStyle(gubun);
		
		HSSFCell nightiybcnttitle = nightiybcnt.createCell(2);
		nightiybcnttitle.setCellValue("방송 건수");
		nightiybcnttitle.setCellStyle(gubun);
		
		HSSFCell nightiybroadpertitle = nightiybroadper.createCell(2);
		nightiybroadpertitle.setCellValue("방송 비율");
		nightiybroadpertitle.setCellStyle(gubun);
		
		HSSFCell nightistudiotitle = nightistudio.createCell(2);
		nightistudiotitle.setCellValue("STUDIO"); 
		nightistudiotitle.setCellStyle(gubun);
		
		HSSFCell nighticastertitle = nighticaster.createCell(2);
		nighticastertitle.setCellValue("CASTER");
		nighticastertitle.setCellStyle(gubun);
	    
		
		for(int i = 0 ; i < 28 ; i++) {
			
			if(i < 14) {
				HSSFCell dayTimeCell = dayTyperow.createCell(i + 3); // 계
	 	    	dayTimeCell.setCellValue(daytimeArr[i]); // title row에 title 삽입
	 	    	dayTimeCell.setCellStyle(dayTypehead); // 스타일 적용
			} else {
				if( i == 14 ) {
					continue;
				} else {
					HSSFCell nightTimeCell = nightTyperow.createCell(i - 11); // 계
					nightTimeCell.setCellValue(daytimeArr[i]); // title row에 title 삽입
					nightTimeCell.setCellStyle(dayTypehead); // 스타일 적용
				}
			}
 	    	
 	    }
		
		
		// 없는 값들을 위해 기본 값 깔기
		for(int i = 0 ; i < 14; i ++) {
			HSSFCell dayAllCell = dayallrow.createCell(i+3);
			dayAllCell.setCellValue(0);
			dayAllCell.setCellStyle(dataStyle);

			HSSFCell nightAllCell = nightallrow.createCell(i+3);
			nightAllCell.setCellValue(0);
			nightAllCell.setCellStyle(dataStyle);

			HSSFCell daysendcntCell = daysendcnt.createCell(i+3);
			HSSFCell daysendperCell = daysendper.createCell(i+3);
			HSSFCell daybrodcntCell = daybrodcnt.createCell(i+3);
			HSSFCell daybrodperCell = daybrodper.createCell(i+3);
			
			daysendcntCell.setCellValue(0);
			daysendperCell.setCellValue(0 + "%");
			daybrodcntCell.setCellValue(0);
			daybrodperCell.setCellValue(0 + "%");
			
			daysendcntCell.setCellStyle(dataStyle);
			daysendperCell.setCellStyle(dataStyle);
			daybrodcntCell.setCellStyle(dataStyle);
			daybrodperCell.setCellStyle(dataStyle);
			
			HSSFCell nightsendcntCell = nightsendcnt.createCell(i+3);
			HSSFCell nightsendperCell = nightsendper.createCell(i+3);
			HSSFCell nightbrodcntCell = nightbrodcnt.createCell(i+3);
			HSSFCell nightbrodperCell = nightbrodper.createCell(i+3);
			
			nightsendcntCell.setCellValue(0);
			nightsendperCell.setCellValue(0 + "%");
			nightbrodcntCell.setCellValue(0);
			nightbrodperCell.setCellValue(0 + "%");
			
			nightsendcntCell.setCellStyle(dataStyle);
			nightsendperCell.setCellStyle(dataStyle);
			nightbrodcntCell.setCellStyle(dataStyle);
			nightbrodperCell.setCellStyle(dataStyle);
		    
		    HSSFCell dayybroadcntCell = dayybroadcnt.createCell(i+3);
			HSSFCell dayybroadperCell = dayybroadper.createCell(i+3);
			HSSFCell dayybroadstudioCell = dayybroadstudio.createCell(i+3);
			HSSFCell dayybroadcasterCell = dayybroadcaster.createCell(i+3);
			HSSFCell daynbroadcntCell = daynbroadcnt.createCell(i+3);
			HSSFCell daynbroadperCell = daynbroadper.createCell(i+3);
			
			dayybroadcntCell.setCellValue(0);
			dayybroadperCell.setCellValue(0 + "%");
			dayybroadstudioCell.setCellValue(0);
			dayybroadcasterCell.setCellValue(0);
			daynbroadcntCell.setCellValue(0);
			daynbroadperCell.setCellValue(0 + "%");
			
			dayybroadcntCell.setCellStyle(dataStyle);
			dayybroadperCell.setCellStyle(dataStyle);
			dayybroadstudioCell.setCellStyle(dataStyle);
			dayybroadcasterCell.setCellStyle(dataStyle);
			daynbroadcntCell.setCellStyle(dataStyle);
			daynbroadperCell.setCellStyle(dataStyle);
		
		    
		    HSSFCell nightybroadcntCell = nightybroadcnt.createCell(i+3);
			HSSFCell nightybroadperCell = nightybroadper.createCell(i+3);
			HSSFCell nightybroadstudioCell = nightybroadstudio.createCell(i+3);
			HSSFCell nightybroadcasterCell = nightybroadcaster.createCell(i+3);
			HSSFCell nightnbroadcntCell = nightnbroadcnt.createCell(i+3);
			HSSFCell nightnbroadperCell = nightnbroadper.createCell(i+3);
			
			nightybroadcntCell.setCellValue(0);
			nightybroadperCell.setCellValue(0 + "%");
			nightybroadstudioCell.setCellValue(0);
			nightybroadcasterCell.setCellValue(0);
			nightnbroadcntCell.setCellValue(0);
			nightnbroadperCell.setCellValue(0 + "%");
			
			nightybroadcntCell.setCellStyle(dataStyle);
			nightybroadperCell.setCellStyle(dataStyle);
			nightybroadstudioCell.setCellStyle(dataStyle);
			nightybroadcasterCell.setCellStyle(dataStyle);
			nightnbroadcntCell.setCellStyle(dataStyle);
			nightnbroadperCell.setCellStyle(dataStyle);

	 	   	
	 	   HSSFCell dayicntCell = dayicnt.createCell(i+3);
			HSSFCell dayiybcntCell = dayiybcnt.createCell(i+3);
			HSSFCell dayiybroadperCell = dayiybroadper.createCell(i+3);
			HSSFCell dayistudioCell = dayistudio.createCell(i+3);
			HSSFCell dayicasterCell = dayicaster.createCell(i+3);
			
			dayicntCell.setCellValue(0);
			dayiybcntCell.setCellValue(0);
			dayiybroadperCell.setCellValue(0 + "%");
			dayistudioCell.setCellValue(0);
			dayicasterCell.setCellValue(0);
			
			dayicntCell.setCellStyle(dataStyle);
			dayiybcntCell.setCellStyle(dataStyle);
			dayiybroadperCell.setCellStyle(dataStyle);
			dayistudioCell.setCellStyle(dataStyle);
			dayicasterCell.setCellStyle(dataStyle);
	 	   
	 	   
	 	  HSSFCell nighticntCell = nighticnt.createCell(i+3);
			HSSFCell nightiybcntCell = nightiybcnt.createCell(i+3);
			HSSFCell nightiybroadperCell = nightiybroadper.createCell(i+3);
			HSSFCell nightistudioCell = nightistudio.createCell(i+3);
			HSSFCell nighticasterCell = nighticaster.createCell(i+3);
			
			nighticntCell.setCellValue(0);
			nightiybcntCell.setCellValue(0);
			nightiybroadperCell.setCellValue(0 + "%");
			nightistudioCell.setCellValue(0);
			nighticasterCell.setCellValue(0);
			
			nighticntCell.setCellStyle(dataStyle);
			nightiybcntCell.setCellStyle(dataStyle);
			nightiybroadperCell.setCellStyle(dataStyle);
			nightistudioCell.setCellStyle(dataStyle);
			nighticasterCell.setCellStyle(dataStyle);
		}
		
		nightallrow.createCell(3).setCellValue("");
		nightsendcnt.createCell(3).setCellValue("");
		nightsendper.createCell(3).setCellValue("");
		nightbrodcnt.createCell(3).setCellValue("");
		nightbrodper.createCell(3).setCellValue("");
		nightybroadcnt.createCell(3).setCellValue("");
	    nightybroadper.createCell(3).setCellValue("");
	    nightybroadstudio.createCell(3).setCellValue("");
	    nightybroadcaster.createCell(3).setCellValue("");
	    nightnbroadcnt.createCell(3).setCellValue("");
	    nightnbroadper.createCell(3).setCellValue("");
	    nighticnt.createCell(3).setCellValue("");
	 	nightiybcnt.createCell(3).setCellValue("");
	 	nightiybroadper.createCell(3).setCellValue("");
	 	nightistudio.createCell(3).setCellValue("");
	 	nighticaster.createCell(3).setCellValue("");
	    
	 	int dayAllsum = 0; // 오전 총 건수
	 	int nightAllsum = 0; // 오후 총 건수
	 	int daySendSum = 0;
	 	int nightSendSum = 0;
	 	double daySendper = 0;
	 	double nightSendper = 0;
	 	int dayBroadsum = 0;
	 	int nightBroadSum = 0;
	 	double dayBrodper = 0;
	 	double nightBrodper = 0;
	 	int dayBroadysum = 0;
	 	int nightBroadysum = 0;
	 	double dayBrodyper = 0; // 방송 비율 (방송요청 아님)
	 	double nightBrodyper = 0;
	 	int dayBroadys = 0;
	 	int dayBroadyc = 0;
	 	int nightBroadys = 0;
	 	int nightBroadyc = 0;
	 	int dayBrodnsum = 0;
	 	int nightBrodnsum = 0;
	 	double dayBrodnper = 0; // 방송 비율 (방송요청 아님)
	 	double nightBrodnper = 0;
	 	    int dayisum = 0;
	 	    int nightisum = 0;
	 	    int dayiybsum = 0; 
	 	    int nightiybsum = 0;
	 	    
	 	    double dayiybper = 0; // 주요 제보 방송 비율
	 	   double nightiybper = 0;
	 	   
	 	    int dayissum = 0;
	 	    int nightissum = 0;
	 	    int dayicsum = 0;
	 	    int nighticsum = 0;
	 	for(int i=0; i < timeDataList.size(); i ++) {
	 		RecordDto record = (RecordDto) timeDataList.get(i);
	 		String receiptTime = record.getString("RECEIPT_TIME");
	 		receiptTime = receiptTime.trim();
	 		int rcpTime = Integer.parseInt(receiptTime);
	 		
	 		for(int j=0; j < 24; j++) {
	 			if(rcpTime == j) {
		 			if(j < 12) { //오전
		 				
		 				
		 				HSSFCell dayallrowCell = dayallrow.createCell(j+5);
		 				dayallrowCell.setCellValue(record.getInt("ALL_CNT")); // 총 접수 건수
		 				dayallrowCell.setCellStyle(dataStyle);
		 				dayAllsum = dayAllsum + record.getInt("ALL_CNT");
		 				
		 				HSSFCell daysendcntCell = daysendcnt.createCell(j+5);
		 				daysendcntCell.setCellValue(record.getInt("SEND_N")); // 자체처리 건수
		 				daysendcntCell.setCellStyle(dataStyle);
		 				daySendSum = daySendSum + record.getInt("SEND_N");
		 				
		 				HSSFCell daysendperCell = daysendper.createCell(j + 5);
		 				double hap = (record.getInt("SEND_N") / (double) record.getInt("ALL_CNT")) * 100;
		 				hap = Math.round(hap * 10.0) / 10.0;
		 				daysendperCell.setCellValue(hap + "%");
		 				daysendperCell.setCellStyle(dataStyle);
		 				daySendper = daySendper + hap;
		 					 		
		 				HSSFCell daybrodcntCell = daybrodcnt.createCell(j+5);
		 				daybrodcntCell.setCellValue(record.getInt("SEND_Y")); // 방송요청 건수
		 				daybrodcntCell.setCellStyle(dataStyle);
		 				dayBroadsum =  dayBroadsum + record.getInt("SEND_Y");
		 						
		 				HSSFCell daybrodperCell =daybrodper.createCell(j + 5);
		 				double hap2 = (record.getInt("SEND_Y") / (double) record.getInt("ALL_CNT")) * 100;
		 				hap2 = Math.round(hap2 * 10.0) / 10.0;
		 				daybrodperCell.setCellValue(hap2 + "%");
		 				daybrodperCell.setCellStyle(dataStyle);
		 				dayBrodper = dayBrodper + hap2;
		 				
		 				HSSFCell dayybroadcntCell = dayybroadcnt.createCell(j+5);
		 				dayybroadcntCell.setCellValue(record.getInt("BROAD_Y")); // 방송 건수
		 				dayybroadcntCell.setCellStyle(dataStyle);
		 				dayBroadysum = dayBroadysum + record.getInt("BROAD_Y");
		 				
		 				HSSFCell dayybroadperCell = dayybroadper.createCell(j + 5);
		 				double hap3 = (record.getInt("BROAD_Y") / (double) record.getInt("SEND_Y")) * 100;
		 				hap3 = Math.round(hap3 * 10.0) / 10.0;
		 				dayybroadperCell.setCellValue(hap3 + "%");
		 				dayybroadperCell.setCellStyle(dataStyle);
		 				dayBrodyper = dayBrodyper + hap3;
		 				
		 				HSSFCell dayybroadstudioCell = dayybroadstudio.createCell(j+5);
		 				dayybroadstudioCell.setCellValue(record.getInt("BROAD_C")); // 스튜디오 방송 건수
		 				dayybroadstudioCell.setCellStyle(dataStyle);
		 			   dayBroadys = dayBroadys + record.getInt("BROAD_C");
		 			   
		 			   
		 			  HSSFCell dayybroadcasterCell = dayybroadcaster.createCell(j+5);
		 			  dayybroadcasterCell.setCellValue(record.getInt("BROAD_P")); // 방송 건수 캐스터
		 			  dayybroadcasterCell.setCellStyle(dataStyle);
		 			  dayBroadyc = dayBroadyc + record.getInt("BROAD_P");
		 			  
		 			 HSSFCell daynbroadcntCell = daynbroadcnt.createCell(j+5);
		 			 daynbroadcntCell.setCellValue(record.getInt("BROAD_N")); // 비방송 건수
		 			 daynbroadcntCell.setCellStyle(dataStyle);
		 			   dayBrodnsum = dayBrodnsum + record.getInt("BROAD_N");
		 			   
		 			   
		 			  HSSFCell daynbroadperCell = daynbroadper.createCell(j + 5);
		 			  double hap4 = (record.getInt("BROAD_N") / (double) record.getInt("SEND_Y")) * 100;
		 				hap4 = Math.round(hap4 * 10.0) / 10.0;
		 				daynbroadperCell.setCellValue(hap4 + "%");
		 				daynbroadperCell.setCellStyle(dataStyle);
		 				dayBrodnper = dayBrodnper + hap4;
		 			   
		 				HSSFCell dayicntCell = dayicnt.createCell(j+5);
		 				dayicntCell.setCellValue(record.getInt("IMP_Y")); // 전송 건수
		 				dayicntCell.setCellStyle(dataStyle);
		 		 	  dayisum = dayisum + record.getInt("IMP_Y");

		 		 	  
		 		 	HSSFCell dayiybcntCell = dayiybcnt.createCell(j+5);
		 		 	dayiybcntCell.setCellValue(record.getInt("IMPBRO_Y")); // 주요제보 방송건수
		 		 	dayiybcntCell.setCellStyle(dataStyle);
			 		 	dayiybsum = dayiybsum +record.getInt("IMPBRO_Y");
			 		 	
			 		 	
			 		 	HSSFCell dayiybroadperCell = dayiybroadper.createCell(j+5);
			 		 	double hap5 = (record.getInt("IMPBRO_Y") / (double) record.getInt("IMP_Y")) * 100;
		 				hap5 = Math.round(hap5 * 10.0) / 10.0;
		 				dayiybroadperCell.setCellValue(hap5 + "%");
		 				dayiybroadperCell.setCellStyle(dataStyle);
		 				dayiybper = dayiybper + hap5;
		 				
		 				
		 				HSSFCell dayistudiorCell =dayistudio.createCell(j+5);
		 				dayistudiorCell.setCellValue(record.getInt("IMPBRO_P")); // 주요제보 스튜디오
		 				dayistudiorCell.setCellStyle(dataStyle);
			 		 	dayissum = dayissum +record.getInt("IMPBRO_P");
			 		 			
			 		 	HSSFCell dayicasterCell = dayicaster.createCell(j+5);
			 		 	dayicasterCell.setCellValue(record.getInt("IMPBRO_C")); // 주요제보 캐스터
			 		 	dayicasterCell.setCellStyle(dataStyle);
			 		 	dayicsum = dayicsum +record.getInt("IMPBRO_C");
			 		 	
			 		 	
			 		 	
			 		 	
		 			} else { //오후
		 				
		 				HSSFCell nightallrowCell = nightallrow.createCell(j-6);
		 				nightallrowCell.setCellValue(record.getInt("ALL_CNT")); // 총 접수 건수
		 				nightallrowCell.setCellStyle(dataStyle);
		 				nightAllsum = nightAllsum + record.getInt("ALL_CNT");
		 				
		 				HSSFCell nightsendcntCell = nightsendcnt.createCell(j-6);
		 				nightsendcntCell.setCellValue(record.getInt("SEND_N")); // 자체처리 건수
		 				nightsendcntCell.setCellStyle(dataStyle);
		 				nightSendSum = nightSendSum + record.getInt("SEND_N");
		 				
		 				HSSFCell nightsendperCell = nightsendper.createCell(j-6);
		 				double hap = (record.getInt("SEND_N") / (double) record.getInt("ALL_CNT")) * 100;
		 				hap = Math.round(hap * 10.0) / 10.0;
		 				nightsendperCell.setCellValue(hap + "%");
		 				nightsendperCell.setCellStyle(dataStyle);
		 				nightSendper = nightSendper + hap;
		 					 		
		 				HSSFCell nightbrodcntCell = nightbrodcnt.createCell(j-6);
		 				nightbrodcntCell.setCellValue(record.getInt("SEND_Y")); // 방송요청 건수
		 				nightbrodcntCell.setCellStyle(dataStyle);
		 				nightBroadSum =  nightBroadSum + record.getInt("SEND_Y");
		 						
		 				HSSFCell nightbrodperCell =nightbrodper.createCell(j-6);
		 				double hap2 = (record.getInt("SEND_Y") / (double) record.getInt("ALL_CNT")) * 100;
		 				hap2 = Math.round(hap2 * 10.0) / 10.0;
		 				nightbrodperCell.setCellValue(hap2 + "%");
		 				nightbrodperCell.setCellStyle(dataStyle);
		 				nightBrodper = nightBrodper + hap2;
		 				
		 				HSSFCell nightybroadcntCell = nightybroadcnt.createCell(j-6);
		 				nightybroadcntCell.setCellValue(record.getInt("BROAD_Y")); // 방송 건수
		 				nightybroadcntCell.setCellStyle(dataStyle);
		 				nightBroadysum = nightBroadysum + record.getInt("BROAD_Y");
		 				
		 				HSSFCell nightybroadperCell = nightybroadper.createCell(j-6);
		 				double hap3 = (record.getInt("BROAD_Y") / (double) record.getInt("SEND_Y")) * 100;
		 				hap3 = Math.round(hap3 * 10.0) / 10.0;
		 				nightybroadperCell.setCellValue(hap3 + "%");
		 				nightybroadperCell.setCellStyle(dataStyle);
		 				nightBrodyper = nightBrodyper + hap3;
		 				
		 				HSSFCell nightybroadstudioCell = nightybroadstudio.createCell(j-6);
		 				nightybroadstudioCell.setCellValue(record.getInt("BROAD_C")); // 스튜디오 방송 건수
		 				nightybroadstudioCell.setCellStyle(dataStyle);
		 				nightBroadys = nightBroadys + record.getInt("BROAD_C");
		 			   
		 			   
		 			  HSSFCell nightybroadcasterCell = nightybroadcaster.createCell(j-6);
		 			 nightybroadcasterCell.setCellValue(record.getInt("BROAD_P")); // 방송 건수 캐스터
		 			nightybroadcasterCell.setCellStyle(dataStyle);
		 			nightBroadyc = nightBroadyc + record.getInt("BROAD_P");
		 			  
		 			 HSSFCell nightnbroadcntCell = nightnbroadcnt.createCell(j-6);
		 			nightnbroadcntCell.setCellValue(record.getInt("BROAD_N")); // 비방송 건수
		 			nightnbroadcntCell.setCellStyle(dataStyle);
		 			nightBrodnsum = nightBrodnsum + record.getInt("BROAD_N");
		 			   
		 			   
		 			  HSSFCell nightnbroadperCell = nightnbroadper.createCell(j-6);
		 			  double hap4 = (record.getInt("BROAD_N") / (double) record.getInt("SEND_Y")) * 100;
		 				hap4 = Math.round(hap4 * 10.0) / 10.0;
		 				nightnbroadperCell.setCellValue(hap4 + "%");
		 				nightnbroadperCell.setCellStyle(dataStyle);
		 				nightBrodnper = nightBrodnper + hap4;
		 			   
		 				HSSFCell nighticntCell = nighticnt.createCell(j-6);
		 				nighticntCell.setCellValue(record.getInt("IMP_Y")); // 전송 건수
		 				nighticntCell.setCellStyle(dataStyle);
		 				nightisum = nightisum + record.getInt("IMP_Y");

		 		 	  
		 		 	HSSFCell nightiybcntCell = nightiybcnt.createCell(j-6);
		 		 	nightiybcntCell.setCellValue(record.getInt("IMPBRO_Y")); // 주요제보 방송건수
		 		 	nightiybcntCell.setCellStyle(dataStyle);
		 		 	nightiybsum = nightiybsum +record.getInt("IMPBRO_Y");
			 		 	
			 		 	
			 		 	HSSFCell nightiybroadperCell = nightiybroadper.createCell(j-6);
			 		 	double hap5 = (record.getInt("IMPBRO_Y") / (double) record.getInt("IMP_Y")) * 100;
		 				hap5 = Math.round(hap5 * 10.0) / 10.0;
		 				nightiybroadperCell.setCellValue(hap5 + "%");
		 				nightiybroadperCell.setCellStyle(dataStyle);
		 				nightiybper = nightiybper + hap5;
		 				
		 				
		 				HSSFCell nightistudiorCell =nightistudio.createCell(j-6);
		 				nightistudiorCell.setCellValue(record.getInt("IMPBRO_P")); // 주요제보 스튜디오
		 				nightistudiorCell.setCellStyle(dataStyle);
		 				nightissum = nightissum +record.getInt("IMPBRO_P");
			 		 			
			 		 	HSSFCell nighticasterCell = nighticaster.createCell(j-6);
			 		 	nighticasterCell.setCellValue(record.getInt("IMPBRO_C")); // 주요제보 캐스터
			 		 	nighticasterCell.setCellStyle(dataStyle);
			 		 	nighticsum = nighticsum +record.getInt("IMPBRO_C");
		 				
		 			}
		 		}
	 		}

	 	}
	 	
	 	
	 	// 합계들 정산 및 엑셀에 넣기
	 	allSum = dayAllsum + nightAllsum; // 오전 오후 총 건수
	 	
	 	HSSFCell dayallrowCell = dayallrow.createCell(3);
	 	dayallrowCell.setCellValue(allSum); // 전체 건수 넣기
	 	dayallrowCell.setCellStyle(dataStyle);

	 	HSSFCell dayallrowCell2 = dayallrow.createCell(4);
	 	dayallrowCell2.setCellValue(dayAllsum); // 전체 건수 넣기
	 	dayallrowCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightallrowCell = nightallrow.createCell(4);
	 	nightallrowCell.setCellValue(nightAllsum); // 전체 건수 넣기
	 	nightallrowCell.setCellStyle(dataStyle);
	 	
	 	
	 	int allSendsum = daySendSum + nightSendSum;// 오전 오후 자체처리 총 건수
	 	HSSFCell daysendcntCell = daysendcnt.createCell(3);
	 	daysendcntCell.setCellValue(allSendsum); // 전체 건수 넣기
	 	daysendcntCell.setCellStyle(dataStyle);

	 	HSSFCell daysendcntCell2 = daysendcnt.createCell(4);
	 	daysendcntCell2.setCellValue(daySendSum); // 전체 건수 넣기
	 	daysendcntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightsendcntCell = nightsendcnt.createCell(4);
	 	nightsendcntCell.setCellValue(nightSendSum); // 전체 건수 넣기
	 	nightsendcntCell.setCellStyle(dataStyle);
	 	
	 	
		double allsendper = ((double) allSendsum/allSum)*100;
		double daysendperval = ((double) daySendSum/dayAllsum)*100;
		double nightsendperval = ((double) nightSendSum/nightAllsum)*100;
		
		allsendper = Math.round(allsendper * 10.0) / 10.0;
		daysendperval = Math.round(daysendperval * 10.0) / 10.0;
		nightsendperval = Math.round(nightsendperval * 10.0) / 10.0;
	 	
	 	HSSFCell daysendperCell = daysendper.createCell(3);
	 	daysendperCell.setCellValue(allsendper+ "%"); // 전체 건수 넣기
	 	daysendperCell.setCellStyle(dataStyle);

	 	HSSFCell daysendperCell2 = daysendper.createCell(4);
	 	daysendperCell2.setCellValue(daysendperval+ "%"); // 전체 건수 넣기
	 	daysendperCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightsendperCell = nightsendper.createCell(4);
	 	nightsendperCell.setCellValue(nightsendperval+ "%");// 전체 건수 넣기
	 	nightsendperCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	
	 	
	 	int allBrodsum = dayBroadsum + nightBroadSum;
	 	daybrodcnt.createCell(3).setCellValue(allBrodsum);
	 	daybrodcnt.createCell(4).setCellValue(dayBroadsum);
	 	nightbrodcnt.createCell(4).setCellValue(nightBroadSum);
	 	
	 	HSSFCell daybrodcntCell = daybrodcnt.createCell(3);
	 	daybrodcntCell.setCellValue(allBrodsum); // 전체 건수 넣기
	 	daybrodcntCell.setCellStyle(dataStyle);

	 	HSSFCell daybrodcntCell2 = daybrodcnt.createCell(4);
	 	daybrodcntCell2.setCellValue(dayBroadsum); // 전체 건수 넣기
	 	daybrodcntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightbrodcntCell = nightbrodcnt.createCell(4);
	 	nightbrodcntCell.setCellValue(nightBroadSum);// 전체 건수 넣기
	 	nightbrodcntCell.setCellStyle(dataStyle);
	 	
	 	double allbrodperval = ((double) allBrodsum/allSum)*100;
	 	double daybrodperval = ((double) dayBroadsum/dayAllsum)*100;
	 	double nightbrodperval = ((double) nightBroadSum/nightAllsum)*100;
	 	allbrodperval = Math.round(allbrodperval * 10.0) / 10.0;
	 	daybrodperval = Math.round(daybrodperval * 10.0) / 10.0;
	 	nightbrodperval = Math.round(nightbrodperval * 10.0) / 10.0;

	 	HSSFCell daybrodperCell = daybrodper.createCell(3);
	 	daybrodperCell.setCellValue(allbrodperval+ "%"); // 전체 건수 넣기
	 	daybrodperCell.setCellStyle(dataStyle);

	 	HSSFCell daybrodperCell2 = daybrodper.createCell(4);
	 	daybrodperCell2.setCellValue(daybrodperval+ "%"); // 전체 건수 넣기
	 	daybrodperCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightbrodperCell = nightbrodper.createCell(4);
	 	nightbrodperCell.setCellValue(nightbrodperval+ "%");// 전체 건수 넣기
	 	nightbrodperCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	int allBrodYsum = dayBroadysum + nightBroadysum;
	 	
	 	HSSFCell dayybroadcntCell = dayybroadcnt.createCell(3);
	 	dayybroadcntCell.setCellValue(allBrodYsum); // 전체 건수 넣기
	 	dayybroadcntCell.setCellStyle(dataStyle);

	 	HSSFCell dayybroadcntCell2 = dayybroadcnt.createCell(4);
	 	dayybroadcntCell2.setCellValue(dayBroadysum); // 전체 건수 넣기
	 	dayybroadcntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightybroadcntCell = nightybroadcnt.createCell(4);
	 	nightybroadcntCell.setCellValue(nightBroadysum);// 전체 건수 넣기
	 	nightybroadcntCell.setCellStyle(dataStyle);
	 		 	
	 	
	 	double allbrodyperval = ((double) allBrodYsum/allBrodsum)*100;
	 	double daybrodyperval = ((double) dayBroadysum/dayBroadsum)*100;
	 	double nightbrodyperval = ((double) nightBroadysum/nightBroadSum)*100;
	 	allbrodyperval = Math.round(allbrodyperval * 10.0) / 10.0;
	 	daybrodyperval = Math.round(daybrodyperval * 10.0) / 10.0;
	 	nightbrodyperval = Math.round(nightbrodyperval * 10.0) / 10.0;
	 	HSSFCell dayybroadperCell = dayybroadper.createCell(3);
	 	dayybroadperCell.setCellValue(allbrodyperval+ "%"); // 전체 건수 넣기
	 	dayybroadperCell.setCellStyle(dataStyle);

	 	HSSFCell dayybroadperCell2 = dayybroadper.createCell(4);
	 	dayybroadperCell2.setCellValue(daybrodyperval+ "%"); // 전체 건수 넣기
	 	dayybroadperCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightybroadperCell = nightybroadper.createCell(4);
	 	nightybroadperCell.setCellValue(nightbrodyperval+ "%");// 전체 건수 넣기
	 	nightybroadperCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	
	 	int allBroadYstudio = dayBroadys + nightBroadys;
	 	
	 	HSSFCell dayybroadstudioCell = dayybroadstudio.createCell(3);
	 	dayybroadstudioCell.setCellValue(allBroadYstudio); // 전체 건수 넣기
	 	dayybroadstudioCell.setCellStyle(dataStyle);

	 	HSSFCell dayybroadstudioCell2 = dayybroadstudio.createCell(4);
	 	dayybroadstudioCell2.setCellValue(dayBroadys); // 전체 건수 넣기
	 	dayybroadstudioCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightybroadstudioCell = nightybroadstudio.createCell(4);
	 	nightybroadstudioCell.setCellValue(nightBroadys);// 전체 건수 넣기
	 	nightybroadstudioCell.setCellStyle(dataStyle);
	 	
	 	int allBroadCstudio = dayBroadyc + nightBroadyc;
	 	
	 	HSSFCell dayybroadcasterCell = dayybroadcaster.createCell(3);
	 	dayybroadcasterCell.setCellValue(allBroadCstudio); // 전체 건수 넣기
	 	dayybroadcasterCell.setCellStyle(dataStyle);

	 	HSSFCell dayybroadcasterCell2 = dayybroadcaster.createCell(4);
	 	dayybroadcasterCell2.setCellValue(dayBroadyc); // 전체 건수 넣기
	 	dayybroadcasterCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightybroadcasterCell = nightybroadcaster.createCell(4);
	 	nightybroadcasterCell.setCellValue(nightBroadyc);// 전체 건수 넣기
	 	nightybroadcasterCell.setCellStyle(dataStyle);
	 		 	
	 	
	 	
	 	int allBroadNo = dayBrodnsum + nightBrodnsum;
	 	
	 	HSSFCell daynbroadcntCell = daynbroadcnt.createCell(3);
	 	daynbroadcntCell.setCellValue(allBroadNo); // 전체 건수 넣기
	 	daynbroadcntCell.setCellStyle(dataStyle);

	 	HSSFCell daynbroadcntCell2 = daynbroadcnt.createCell(4);
	 	daynbroadcntCell2.setCellValue(dayBrodnsum); // 전체 건수 넣기
	 	daynbroadcntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightnbroadcntCell = nightnbroadcnt.createCell(4);
	 	nightnbroadcntCell.setCellValue(nightBrodnsum);// 전체 건수 넣기
	 	nightnbroadcntCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	double allbrodnperval = ((double) allBroadNo/allBrodsum)*100;
	 	double daybrodnperval = ((double) dayBrodnsum/dayBroadsum)*100;
	 	double nightbrodnperval = ((double) nightBrodnsum/nightBroadSum)*100;
	 	allbrodnperval = Math.round(allbrodnperval * 10.0) / 10.0;
	 	daybrodnperval = Math.round(daybrodnperval * 10.0) / 10.0;
	 	nightbrodnperval = Math.round(nightbrodnperval * 10.0) / 10.0;
	 	
	 	HSSFCell daynbroadperCell = daynbroadper.createCell(3);
	 	daynbroadperCell.setCellValue(allbrodnperval+ "%"); // 전체 건수 넣기
	 	daynbroadperCell.setCellStyle(dataStyle);

	 	HSSFCell daynbroadperCell2 = daynbroadper.createCell(4);
	 	daynbroadperCell2.setCellValue(daybrodnperval+ "%"); // 전체 건수 넣기
	 	daynbroadperCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightnbroadperCell = nightnbroadper.createCell(4);
	 	nightnbroadperCell.setCellValue(nightbrodnperval+ "%");// 전체 건수 넣기
	 	nightnbroadperCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	int allImpSend = dayisum + nightisum;  // 전송 건수
	 	
	 	
	 	HSSFCell dayicntCell = dayicnt.createCell(3);
	 	dayicntCell.setCellValue(allImpSend); // 전체 건수 넣기
	 	dayicntCell.setCellStyle(dataStyle);

	 	HSSFCell dayicntCell2 = dayicnt.createCell(4);
	 	dayicntCell2.setCellValue(dayisum); // 전체 건수 넣기
	 	dayicntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nighticntCell = nighticnt.createCell(4);
	 	nighticntCell.setCellValue(nightisum);// 전체 건수 넣기
	 	nighticntCell.setCellStyle(dataStyle);
	 	
	 	
	 	int allImpBrod = dayiybsum + nightiybsum; // 방송 건수

	 	HSSFCell dayiybcntCell = dayiybcnt.createCell(3);
	 	dayiybcntCell.setCellValue(allImpBrod); // 전체 건수 넣기
	 	dayiybcntCell.setCellStyle(dataStyle);

	 	HSSFCell dayiybcntCell2 = dayiybcnt.createCell(4);
	 	dayiybcntCell2.setCellValue(dayiybsum); // 전체 건수 넣기
	 	dayiybcntCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightiybcntCell = nightiybcnt.createCell(4);
	 	nightiybcntCell.setCellValue(nightiybsum);// 전체 건수 넣기
	 	nightiybcntCell.setCellStyle(dataStyle);
	 	
	 	
	 	
	 	
	 	double allimbrodperval = ((double) allImpBrod/allImpSend)*100;
	 	double dayimbrodperval = ((double) dayiybsum/dayisum)*100;
	 	double nightimbrodperval = ((double) nightiybsum/nightisum)*100;
	 	allimbrodperval = Math.round(allimbrodperval * 10.0) / 10.0;
	 	dayimbrodperval = Math.round(dayimbrodperval * 10.0) / 10.0;
	 	nightimbrodperval = Math.round(nightimbrodperval * 10.0) / 10.0;
	 	
	 	HSSFCell dayiybroadperCell = dayiybroadper.createCell(3);
	 	dayiybroadperCell.setCellValue(allimbrodperval+ "%"); // 전체 건수 넣기
	 	dayiybroadperCell.setCellStyle(dataStyle);

	 	HSSFCell dayiybroadperCell2 = dayiybroadper.createCell(4);
	 	dayiybroadperCell2.setCellValue(dayimbrodperval+ "%"); // 전체 건수 넣기
	 	dayiybroadperCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightiybroadperCell = nightiybroadper.createCell(4);
	 	nightiybroadperCell.setCellValue(nightimbrodperval+ "%");// 전체 건수 넣기
	 	nightiybroadperCell.setCellStyle(dataStyle);
	 	
	 	int allImpStudio = dayissum + nightissum;

	 	HSSFCell dayistudioCell = dayistudio.createCell(3);
	 	dayistudioCell.setCellValue(allImpStudio); // 전체 건수 넣기
	 	dayistudioCell.setCellStyle(dataStyle);

	 	HSSFCell dayistudioCell2 = dayistudio.createCell(4);
	 	dayistudioCell2.setCellValue(dayissum); // 전체 건수 넣기
	 	dayistudioCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nightistudioCell = nightistudio.createCell(4);
	 	nightistudioCell.setCellValue(nightissum);// 전체 건수 넣기
	 	nightistudioCell.setCellStyle(dataStyle);
	 	
	 	int allImpCaster = dayicsum + nighticsum;
	 	
	 	HSSFCell dayicasterCell = dayicaster.createCell(3);
	 	dayicasterCell.setCellValue(allImpCaster); // 전체 건수 넣기
	 	dayicasterCell.setCellStyle(dataStyle);

	 	HSSFCell dayicasterCell2 = dayicaster.createCell(4);
	 	dayicasterCell2.setCellValue(dayicsum); // 전체 건수 넣기
	 	dayicasterCell2.setCellStyle(dataStyle);
	 	
	 	HSSFCell nighticasterCell = nighticaster.createCell(4);
	 	nighticasterCell.setCellValue(nighticsum);// 전체 건수 넣기
	 	nighticasterCell.setCellStyle(dataStyle);
	 	
 	    
 	    
    	// 기간(일) 구하기
 	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
 	   
 	    LocalDate start = LocalDate.parse(startDate, formatter); //date 형으로 변환
 	    LocalDate end = LocalDate.parse(endDate, formatter);
 	    
 	    long daysBetween = ChronoUnit.DAYS.between(start, end); // 두 날짜 사의 값 구하기
 	    int daysCntval = (int) daysBetween + 1; // 명시적 변환 => 연산을 위해 int 형으로 형 변환
 	    int dayCnt = 0;
 	    
 	    if(daysCntval < 1) {
 	    	dayCnt = allSum;
 	    } else {
 	    	dayCnt = allSum / daysCntval;
 	    }
 	    
 	    
	    CellStyle daycntStyle = wb.createCellStyle();
	    daycntStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 오른쪽 정렬 (가로 기준)
	    daycntStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 중앙 정렬 (세로 기준)
	    
	    Font daycntfont = wb.createFont(); // 폰트 객체 생성
	    daycntfont.setFontHeightInPoints((short) 12); // 폰트 크기 설정   
	    daycntfont.setFontName("굴림체");
	    daycntStyle.setFont(daycntfont); // 폰트 스타일을 셀 스타일에 적용
	    
        // 일당 교통정보 현황 건수 row (A4) => 총 접수 건수 / 기간
        HSSFRow daycntrow = sheet.createRow(3);
        HSSFCell daycntcell = daycntrow.createCell(0);
        
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3)); // title cell 병합 / 예제 파일 기준으로 5칸 병합
        daycntcell.setCellValue(" ■ 교통정보 현황: " + dayCnt + "건 / 일");
        daycntcell.setCellStyle(daycntStyle); // 스타일 적용
		
        // cell width ,  height 변경
 	    sheet.setColumnWidth(0, 3700); // setColumnWidth(변경 할 cell , 크기)
 	    daycntrow.setHeightInPoints(25.88f); //setHeightInPoints 크기)

    }
    
    
}