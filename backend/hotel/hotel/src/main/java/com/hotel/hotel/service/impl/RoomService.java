package com.hotel.hotel.service.impl;

import com.hotel.hotel.dto.Response;
import com.hotel.hotel.dto.RoomDTO;
import com.hotel.hotel.entity.Room;
import com.hotel.hotel.exception.OurException;
import com.hotel.hotel.repo.BookingRepository;
import com.hotel.hotel.repo.RoomRepository;
import com.hotel.hotel.service.CloudinaryService;
import com.hotel.hotel.service.interfac.IRoomService;
import com.hotel.hotel.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Response addNewRoom(MultipartFile photo,String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();
        try {
            String imageUrl = cloudinaryService.saveImageToCloud(photo);
            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomDescription(description);
            room.setRoomPrice(roomPrice);
            room.setRoomType(roomType);
            Room savedRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(savedRoom);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response getAllRooms() {
        Response response = new Response();
        try{
            List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomDTOList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {
        Response response = new Response();
        try{
            roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            roomRepository.deleteById(roomId);
            response.setStatusCode(200);
            response.setMessage("Successful");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a room" + e.getMessage());
        }

        return  response;
    }

    @Override
    public Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
        Response response = new Response();
        try{
            String imageUrl = null;
            if(photo != null && !photo.isEmpty()) {
                imageUrl = cloudinaryService.saveImageToCloud(photo);
            }
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            if(roomType != null) room.setRoomType(roomType);
            if(roomPrice != null) room.setRoomPrice(roomPrice);
            if(description != null) room.setRoomDescription(description);
            if(imageUrl != null) room.setRoomPhotoUrl(imageUrl);

            Room updatedRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(updatedRoom);

            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomDTO);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a room" + e.getMessage());
        }

        return  response;
    }

    @Override
    public Response getRoomById(Long roomId) {
        Response response = new Response();
        try{
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTOPlusBookings(room);

            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomDTO);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a room" + e.getMessage());
        }

        return  response;
    }

    @Override
    public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        Response response = new Response();

        try{
            List<Room> availableRooms = roomRepository.findAvailableRoomsByDatesAndTypes(checkInDate,checkOutDate,roomType);
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(availableRooms);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomDTOList);
        }  catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a room" + e.getMessage());
        }
        return  response;
    }

    @Override
    public Response getAllAvailableRooms() {
        Response response = new Response();
        try{
            List<Room> roomList = roomRepository.getAllAvailableRooms();
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomDTOList);
        }  catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a room" + e.getMessage());
        }
        return  response;
    }
}
