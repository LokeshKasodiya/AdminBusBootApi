package org.jsp.adminbusapp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository busRepository;

	public Bus saveBus(Bus bus) {
		return busRepository.save(bus);
	}
	public Optional<Bus> findById(int id){
		return busRepository.findById(null);
	}
	public List<Bus> findByAdmintId(int id) {
		return busRepository.findByAdminId(id);
	}

	public List<Bus> findBus(String from_location, String to_location, LocalDate date_of_departure) {
		return busRepository.findBus(date_of_departure, from_location, to_location);
	}

	public List<Bus> findByDateOfDeparture(LocalDate date_Of_Departute) {
		return busRepository.findByDateOfDeparture(date_Of_Departute);
	}

	public Optional<Bus> findByBusNumber(String bus_no) {
		return busRepository.findByBusNumber(bus_no);
	}

	public List<Bus> findByTravelsName(String name) {
		return busRepository.findByBusTravelsName(name);
	}
}
