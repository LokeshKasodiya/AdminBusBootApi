package org.jsp.adminbusapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {
	@Autowired
	private BusService busService;

	@PostMapping("/buses/{admin_id}")
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus, @PathVariable int admin_id) {
		return busService.saveBus(bus, admin_id);
	}

	@PutMapping("/buses")
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus) {
		return busService.updateBus(bus);
	}

	@GetMapping("/buses/findByAdmin/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByAdminId(@PathVariable int admin_id) {
		return busService.findBusByAdminID(admin_id);
	}

	@GetMapping("/buses/findByBusNumber/{number}")
	public ResponseEntity<ResponseStructure<Bus>> findByBusNo(@PathVariable String number) {
		return busService.findBusByBusNo(number);
	}

	@GetMapping("/buses/findByTravelsName/{travels_name}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByAdminId(@PathVariable String travels_name) {
		return busService.findBusTravelsName(travels_name);
	}

	@GetMapping("/buses/searchBuses")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBus(@RequestParam String from_loc,
			@RequestParam String to_loc, @RequestParam String date) {
		LocalDate date_of_departure = LocalDate.parse(date);
		return busService.findBus(from_loc, to_loc, date_of_departure);
	}

	@GetMapping("buses/findByDate/{date}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDate(@PathVariable String date) {
		LocalDate date_of_departure = LocalDate.parse(date);
		return busService.findBusByDateOfDeparture(date_of_departure);
	}

}
