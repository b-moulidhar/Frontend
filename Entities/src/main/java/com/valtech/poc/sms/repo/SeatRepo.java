package com.valtech.poc.sms.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Seat;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer>{

	int findIdBysName(String sName);
//	 @Query(value = "SELECT s.s_name AS seat_name, COUNT(*) AS popular_seats " +
//             "FROM seats_booked sb " +
//             "INNER JOIN seat s ON s.s_id = sb.s_id " +
//             "GROUP BY s.s_id " +
//             "ORDER BY popular_seats DESC " +
//             "LIMIT 5")
//         List<Object[]> findTopFivePopularSeats();

	
}
