'use strict';

angular.module('bookApp').controller('bookController', function ($scope, $http, $window) {

	$scope.pageLink = "";
	
	//Initialize the boolean that displays the action <delete> and <update>
	function disableAction() {
		$scope.deleteAction = false;
		$scope.updateAction = false;
		$scope.action = false;
	}
	
	//this method call the webservice that retrieve all the books 
	//from the database (filtered by the date of publication and title).
	
	$scope.displayListBook = function () {
		$scope.pageLink = "../view/list_book.html";
		$http.get("http://localhost:8080/user/books")
		.then(function(response) {
			$scope.listBooks = response.data;
		});
		disableAction();
	}
	
	//Search book by its title
	$scope.displayBook = function () {
		$scope.bookTitle = angular.element(document.querySelector('#bookId')).val();
		$scope.pageLink = "../view/list_book.html";
		$http.get("http://localhost:8080/user/searchbook?booktitle="+$scope.bookTitle)
		.then(function(response) {
			$scope.listBooks = response.data;
		});
	}
	
	//The method searchBook is shared by update, delete and search actions
	$scope.searchBook = function (type) {
		$scope.bookTitle = "";
		if(type==="delete"){
			$scope.deleteAction = true;
			$scope.action = true;
			$scope.updateAction = false;
		} else if(type==="update"){
			$scope.updateAction = true;
			$scope.action = true;
			$scope.deleteAction = false;
			$scope.buttonTitle = "Update";
		} else {
			disableAction();
		}
		$scope.pageLink = "../view/search_book.html";
		$scope.listBooks = "";
	}
	
	//Display the screen for updating a book
	$scope.editBook = function (bookId, title, author, pages, publicationDate) {
		$scope.bookId = bookId;
		$scope.title = title;
		$scope.author = author;
		$scope.pages = pages;
		$scope.publicationDate = publicationDate;
		$scope.pageLink = "../view/edit_book.html";	
	}
	
	//Call the web service to delete a book
	$scope.deleteBook = function (bookId) {
		$scope.pageLink = "../view/list_book.html";	
		$http.delete("http://localhost:8080/admin/deletebook?bookId="+bookId)
		.then(function(response) {
			$scope.save = "deleted";
			$scope.pageLink ="../view/display_message.html";
		}, function (error) {
			$scope.pageLink = "../view/403.html";
        });
	}
	
	//Save or Update books
	$scope.saveOrUpdateBook = function (bookId, title, author, pages, publicationDate) {
		
		var book = {
				"bookId" : bookId,
				"title" :  title,
				"author" : author,
				"pages" :  pages,
				"publicationDate" : publicationDate
		};

		if(bookId!==null && bookId!==undefined) {
			$http.put("http://localhost:8080/user/updatebook", JSON.stringify(book))
			.then(function(response) {
			});
			$scope.save = "updated";
		} else if(book!==null) {
			$http.post("http://localhost:8080/user/savebook", JSON.stringify(book))
			.then(function(response) {
			});
			$scope.save = "saved";
		}
		$scope.bookTitle = title;
		reset();
		$scope.pageLink = "../view/display_message.html";
	}
	
	//Initialize some variables
	function reset() {
		$scope.bookId = null;
		$scope.title = "";
		$scope.author = "";
		$scope.pages = null;
		$scope.publicationDate = null;
	}
	
	//formular to add new book
	$scope.bookForm = function () {
		$scope.pageLink = "../view/edit_book.html";
		$scope.buttonTitle = "Save";
	}
	
	//logout from the application
	$scope.logout = function() {
		$http.get("http://localhost:8080/user/logout")
		.then(function(response) {
			$window.location.href = 'http://localhost:8080/user/login.html';
		});
	}
});
