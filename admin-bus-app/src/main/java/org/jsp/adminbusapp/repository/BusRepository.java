package org.jsp.adminbusapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	@Query("select b from Bus b where b.date_of_departure=?1 and b.from_location=?2 and b.to_location=?3")
	public List<Bus> findBus(LocalDate date_of_departure, String from_location, String to_location);

	@Query("select b from Bus b where b.admin.id=?1")
	public List<Bus> findByAdminId(int id);

	@Query("select b from Bus b where b.date_of_departure=?1")
	public List<Bus> findByDateOfDeparture(LocalDate date_of_departure);

	@Query("select b from Bus b where b.bus_no=?1")
	public Optional<Bus> findByBusNumber(String bus_no);

	@Query("select b from Bus b where b.admin.travels_name=?1")
	public List<Bus> findByBusTravelsName(String name);
}
