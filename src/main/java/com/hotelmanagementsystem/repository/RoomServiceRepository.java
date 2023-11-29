package com.hotelmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.RoomService;

@Repository
public interface RoomServiceRepository extends JpaRepository<RoomService, Integer>{



}
