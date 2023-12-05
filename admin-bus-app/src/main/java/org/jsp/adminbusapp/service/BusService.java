package org.jsp.adminbusapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dao.BusDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.exception.AdminNotFoundException;
import org.jsp.adminbusapp.service.exception.BusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Bus>> saveBus(Bus bus, int admin_id) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		if (recAdmin.isPresent()) {
			Admin admin = recAdmin.get();
			admin.getBuses().add(bus);
			bus.setAdmin(admin);
			adminDao.saveAdmin(admin);
			structure.setData(busDao.saveBus(bus));
			structure.setMessage("Bus Added successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.CREATED);
		}
		throw new AdminNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus){
		ResponseStructure<Bus> structure=new ResponseStructure<>();
		Optional<Bus> recBus=busDao.findById(bus.getId());
		if(recBus.isPresent()) {
			Bus dbBus=recBus.get();
			dbBus.setId(bus.getId());
			dbBus.setBus_no(bus.getBus_no());
			dbBus.setCost_per_seat(bus.getCost_per_seat());
			dbBus.setDate_of_departure(bus.getDate_of_departure());
			dbBus.setFrom_location(bus.getFrom_location());
			dbBus.setTo_location(bus.getTo_location());
			dbBus.setAdmin(bus.getAdmin());
			dbBus.setImage_url(bus.getImage_url());
			dbBus.setNo_of_seats(dbBus.getNo_of_seats());
			structure.setData(busDao.saveBus(dbBus));
			structure.setMessage("Bus details Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.ACCEPTED);
		}
		throw new BusNotFoundException("Bus Not found with given Id") ;
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findBus(String from_loc,String to_loc,LocalDate date_of_depatrure){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findBus(from_loc, to_loc, date_of_depatrure);
		if(buses.size()>0) {
			structure.setData(buses);
			structure.setMessage("Buses Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("No buses Available");
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByAdminID(int id){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findByAdmintId(id);
		if(buses.size()>0) {
			structure.setData(buses);
			structure.setMessage("Buses Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("No buses found with given admin id");
	}
	public ResponseEntity<ResponseStructure<Bus>> findBusByBusNo(String number){
		ResponseStructure<Bus> structure=new ResponseStructure<>();
		Optional<Bus> recBus=busDao.findByBusNumber(number);
		if(recBus.isPresent()) {
			structure.setData(recBus.get());
			structure.setMessage("Buses Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("No buses Available with given Bus Number");
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusTravelsName(String name){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findByTravelsName(name);
		if(buses.size()>0) {
			structure.setData(buses);
			structure.setMessage("Buses Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("No buses Available for given travels name");
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDateOfDeparture(LocalDate date){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findByDateOfDeparture(date);
		if(buses.size()>0) {
			structure.setData(buses);
			structure.setMessage("Buses Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("No buses Available on given date");
	}
	
}
