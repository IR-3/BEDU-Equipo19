package org.bedu.java.backend.pet;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.service.CCitaService;
import org.bedu.java.backend.pet.service.CMascotaService;
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

	@Autowired
	private CMascotaService cMascotaService;

	@Autowired
	private CCitaService cCitaService;

	@Override
	@Transactional
	public void run(String... args) throws Exception
	{
		cargarTutores();
		cargarMascotas();
		cargarVeterinarios();
		cargarCitas();
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

	private void cargarMascotas() throws IOException
	{

		Resource resource = new ClassPathResource("Mascotas.xlsx");
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

					CMascotaDTOCreate mascotaDTO = new CMascotaDTOCreate();

					
					mascotaDTO.setStrNombre(getStringCellValue(row.getCell(0)));
					mascotaDTO.setStrEspecie(getStringCellValue(row.getCell(1)));
					mascotaDTO.setStrRaza(getStringCellValue(row.getCell(2)));
					String IdTutor = getStringCellValue(row.getCell(3));
					if(IdTutor != null && !IdTutor.isEmpty())
					{
					mascotaDTO.setLngTutorID(Long.parseLong(IdTutor));
					}

					if (mascotaDTO.getStrNombre() != null) {
						cMascotaService.Nuevo(mascotaDTO);
					}
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

	private void cargarCitas() throws IOException
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	

		Resource resource = new ClassPathResource("Citas.xlsx");
		File file = resource.getFile();

		try (FileInputStream fileInputStream = new FileInputStream(file))
		{
			
			try (Workbook workbook = new XSSFWorkbook(fileInputStream)) 
			{
				Sheet sheet = workbook.getSheetAt(0);//hoja donde estan los datos

				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						continue;
					}
				
					CCitaDTOCreate citaDTO = new CCitaDTOCreate();
				
					// Asegúrate de que las celdas que contienen las fechas y horas no estén vacías
					String fechaString = getStringCellValue(row.getCell(0));
					String horaString = getStringCellValue(row.getCell(1));
				
					if (fechaString != null && !fechaString.isEmpty() && horaString != null && !horaString.isEmpty()) {
						System.out.println("Fecha: " + fechaString + ", Hora: " + horaString);
				
						citaDTO.setClsDate(LocalDate.parse(fechaString, dateFormatter));
						citaDTO.setClsTime(LocalTime.parse(horaString, timeFormatter));
					} else {
						// Manejar el caso en que la fecha o la hora sean nulas o vacías
						System.err.println("ADVERTENCIA: La fecha o la hora son nulas o vacías.");
					}
				
					citaDTO.setStrTratamiento(getStringCellValue(row.getCell(2)));
					String MascotaId = getStringCellValue(row.getCell(3));
					if(MascotaId != null && !MascotaId.isEmpty())
					{
					citaDTO.setLngMascotaID(Long.parseLong(MascotaId));
					}
					String VeterinarioId = getStringCellValue(row.getCell(4));
					if(VeterinarioId != null && !VeterinarioId.isEmpty())
					{
					citaDTO.setLngVetID(Long.parseLong(VeterinarioId));
					}
					if(citaDTO.getLngVetID() != 0)
					{
					cCitaService.Nuevo(citaDTO);
					}
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

//comentario extra