package com.valtech.poc.sms.dao;

import java.time.LocalDateTime;

public interface AdminDao {

	int getFoodCount(LocalDateTime dateTime);

	int getSeatBookedCount(LocalDateTime dateTime);

}