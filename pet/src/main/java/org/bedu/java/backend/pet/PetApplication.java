package org.bedu.java.backend.pet;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.service.CTutorService;
import org.bedu.java.backend.pet.service.CVeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.util.Date;


import org.springframework.core.io.Resource;
import org.apache.poi.ss.usermodel.*;




@SpringBootApplication
public class PetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PetApplication.class, args);
	}

	@Autowired
	private CTutorService cTutorService;

	@Autowired
	private CVeterinarioService cVeterinarioService;

	@Override
	@Transactional
	public void run(String... args) throws Exception
	{
		cargarTutores();
		cargarVeterinarios();
	}

	private void cargarTutores() throws IOException
	{

		Resource resource = new ClassPathResource("Tutores.xlsx");
		File file = resource.getFile();

		try (FileInputStream fileInputStream = new FileInputStream(file))
		{
			
			try (Workbook workbook = new XSSFWorkbook(fileInputStream)) 
			{
				Sheet sheet = workbook.getSheetAt(0);//hoja donde estan los datos

				for(Row row : sheet)
				{
					if(row.getRowNum() == 0)
					{
						continue;
					}
					CTutorDTOCreate tutorDTO = new CTutorDTOCreate();
					CPersonaDTOCreate personaDTO = new CPersonaDTOCreate();
					personaDTO.setStrNombre(getStringCellValue(row.getCell(0)));
					personaDTO.setStrPaterno(getStringCellValue(row.getCell(1)));
					personaDTO.setStrMaterno(getStringCellValue(row.getCell(2)));
					personaDTO.setStrEmail(getStringCellValue(row.getCell(3)));
					personaDTO.setStrTelefono(getStringCellValue(row.getCell(4)));
					tutorDTO.setClsTutor(personaDTO);
					cTutorService.Nuevo(tutorDTO);
				}
			}
		}catch (IOException e) 
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void cargarVeterinarios() throws IOException
	{

		Resource resource = new ClassPathResource("Veterinarios.xlsx");
		File file = resource.getFile();

		try (FileInputStream fileInputStream = new FileInputStream(file))
		{
			
			try (Workbook workbook = new XSSFWorkbook(fileInputStream)) 
			{
				Sheet sheet = workbook.getSheetAt(0);//hoja donde estan los datos

				for(Row row : sheet)
				{
					if(row.getRowNum() == 0)
					{
						continue;
					}
					//CTutorDTOCreate tutorDTO = new CTutorDTOCreate();
					//CPersonaDTOCreate personaDTO = new CPersonaDTOCreate();
					//personaDTO.setStrNombre(getStringCellValue(row.getCell(0)));
					CVeterinarioDTOCreate veterinarioDTO = new CVeterinarioDTOCreate();
					CPersonaDTOCreate personaDTO = new CPersonaDTOCreate();

					personaDTO.setStrNombre(getStringCellValue(row.getCell(0)));
					personaDTO.setStrPaterno(getStringCellValue(row.getCell(1)));
					personaDTO.setStrMaterno(getStringCellValue(row.getCell(2)));
					personaDTO.setStrEmail(getStringCellValue(row.getCell(3)));
					personaDTO.setStrTelefono(getStringCellValue(row.getCell(4)));

					veterinarioDTO.setClsPersona(personaDTO);
					veterinarioDTO.setStrCedula(getStringCellValue(row.getCell(5)));
					veterinarioDTO.setStrEspecialidad(getStringCellValue(row.getCell(6)));
					
					cVeterinarioService.Nuevo(veterinarioDTO);
					//tutorDTO.setClsTutor(personaDTO);
					//cTutorService.Nuevo(tutorDTO);
					//cVeterinarioService.
				}
			}
		}catch (IOException e) 
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	///carga de datos celdas excel////////////////////////////////////
	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
	
		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					// Verificar si la celda tiene el formato de hora y no de fecha
					if (cell.getCellStyle().getDataFormatString().contains("h:mm")) {
						return formatTimeCell(cell.getNumericCellValue());
					} else {
						// Formatear la fecha según el formato original del Excel
						Date date = cell.getDateCellValue();
						DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
						return originalFormat.format(date);
					}
				} else {
					// Si la celda es numérica pero no es una fecha, conviértela a cadena sin ".0"
					double numericValue = cell.getNumericCellValue();
					long longValue = (long) numericValue;
	
					if (numericValue == longValue) {
						return String.valueOf(longValue);  // Es un número entero
					} else {
						return String.valueOf(numericValue);  // Es un número con decimales
					}
				}
			case BOOLEAN:
				// Si la celda es booleana, conviértela a cadena
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				// Puedes manejar fórmulas según tus requisitos
				return cell.getCellFormula();
			default:
				return null;
		}
	}
	
	private String formatTimeCell(double numericValue) {
		// Convertir el valor numérico a horas y minutos
		int hours = (int) (numericValue * 24);
		int minutes = (int) ((numericValue * 24 * 60) % 60);
	
		// Formatear la hora como cadena
		return String.format("%02d:%02d", hours, minutes);
	}
}

