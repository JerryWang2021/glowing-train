$(document).ready(function(){
    loadDvds();
    // addDvd();
    // updateDvd();
    loadSerchResults();
});

function loadDvds() {
  clearDvdTable();
  var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvds',
        success: function(dvdArray) {
          $.each(dvdArray, function(index, dvd){
              var title = dvd.title;
              var releaseYear = dvd.releaseYear;
              var director = dvd.director;
              var rating = dvd.rating;
              var id = dvd.id;

              var row = '<tr>';
                  row += '<td><button type="button" class="btn btn-link" onclick="showDvdContent('+ id +')">'+ title + '</button></td>';
                  row += '<td>' + releaseYear + '</td>';
                  row += '<td>' + director + '</td>';
                  row += '<td>' + rating + '</td>';
                  row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + id +')">Edit</button></td>';
                  row += '<td><button type="button" class="btn btn-danger" onclick="deleteDvd(' + id + ')">Delete</button></td>';
                  row += '</tr>';

                contentRows.append(row);
          })

      },
      error: function() {
        $('#errorMessages')
              .append($('<li>')
              .attr({class: 'list-group-item list-group-item-danger'})
              .text('Error calling web service.  Please try again later.'));
      }
    });
}

// function showEditForm() {
//     $('#errorMessages').empty();
//
//     $('#dvdTableDiv').hide();
//     $('#editFormDiv').show();
// }

function showAddForm() {
    $('#errorMessages').empty();

    $('#dvdTableDiv').hide();
    $('#addFormDiv').show();
}

function hideEditForm() {
    $('#errorMessages').empty();

    $('#edittitle').val('');
    $('#editreleaseYear').val('');
    $('#editdirector').val('');
    $('#editrating').val('');
    $('#editnotes').val('');

    $('#dvdTableDiv').show();
    $('#editFormDiv').hide();
}

function hideAddForm() {
    $('#errorMessages').empty();

    $('#addtitle').val('');
    $('#addreleaseyear').val('');
    $('#adddirector').val('');
    $('#addrating').val('');
    $('#addnotes').val('');

    $('#dvdTableDiv').show();
    $('#addFormDiv').hide();
}

function hideDvdContentForm(){
  $('#errorMessages').empty();

  $('#title').val('');
  $('#releaseyear').val('');
  $('#director').val('');
  $('#rating').val('');
  $('#notes').val('');

  $('#dvdTableDiv').show();
  $('#dvdContentDiv').hide();

}

// function addDvd() {
   $('#addButton').click(function (event) {
        $.ajax({
          type: 'POST',
          url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd',
          data: JSON.stringify({
            title: $('#addtitle').val(),
            releaseYear: $('#addreleaseyear').val(),
            director: $('#adddirector').val(),
            rating: $('#addrating').val(),
            notes: $('#addnotes').val()
          }),
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          'dataType': 'json',
           success: function() {
               $('#errorMessages').empty();
               $('#addtitle').val('');
               $('#addreleaseyear').val('');
               $('#adddirector').val('');
               $('#addrating').val('');
               $('#addnotes').val('');
               loadDvds();
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
           }
        });
   })
// }

function clearDvdTable() {
    $('#contentRows').empty();
}

function showEditForm(id) {
    $('#errorMessages').empty();
    $.ajax({
        type: 'GET',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + id,
        success: function(data, status) {
            $('#edititle').val(data.title);
            $('#editreleaseYear').val(data.releaseYear);
            $('#editdirector').val(data.director);
            $('#editrating').val(data.rating);
            $('#editnotes').val(data.notes);
            $('#editid').val(data.id);

        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
        }
    })

    $('#dvdTableDiv').hide();
    $('#editFormDiv').show();
}

function showDvdContent(id) {
    $('#errorMessages').empty();
    $.ajax({
        type: 'GET',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + id,
        success: function(data, status) {
            $('#title').val(data.title);
            $('#releaseYear').val(data.releaseYear);
            $('#director').val(data.director);
            $('#rating').val(data.rating);
            $('#notes').val(data.notes);
            $('#id').val(data.id);

        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
        }
    })

    $('#dvdTableDiv').hide();
    $('#dvdContentDiv').show();
}

// function updateDvd() {
    $('#updateButton').click(function(event) {
        // var haveValidationErrors = checkAndDisplayValidationErrors($('#editForm').find('input'));
        //
        // if(haveValidationErrors) {
        //   return false;
        // }

        let tobeUpdateData = {
              id: $('#editid').val(),
              title: $('#edittitle').val(),
              releaseYear: $('#editreleaseYear').val(),
              director: $('#editdirector').val(),
              rating: $('#editrating').val(),
              notes: $('#editnotes').val()
          };

    return  $.ajax({
            type: 'PUT',
            url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/'+ $('#editid').val(),
            data: JSON.stringify(
              tobeUpdateData
            ),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            'success': function() {
                $('#errorMessage').empty();
                // hideEditForm();
                loadDvds();
            },
            'error': function() {
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
            }
        });
    })
  // }

  function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

    var errorMessages = [];

    input.each(function() {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    if (errorMessages.length > 0){
        $.each(errorMessages,function(index,message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}

function deleteDvd(id) {
  if (confirm("Are you sure you want to delete this Dvd from collection?")){
    $.ajax({
        type: 'DELETE',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + id,
        success: function() {
          loadDvds();
        }
    });
  }
  else {
      alert("Your delete of this dvd was cancelled!");
  }

 }

 function loadSerchResults() {
   $('#searchButton').click(function(event) {
   clearDvdTable();
   let contentRows = $('#contentRows');

   let select = $('#categarySelect').val();
   let search = $('#searchInput').val();

     $.ajax({
         type: 'GET',
         url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvds/'+ select + '/' + search,
         success: function(dvdArray) {
           $.each(dvdArray, function(index, dvd){
               var title = dvd.title;
               var releaseYear = dvd.releaseYear;
               var director = dvd.director;
               var rating = dvd.rating;
               var id = dvd.id;

               var row = '<tr>';
                   row += '<td>' + title + '</td>';
                   row += '<td>' + releaseYear + '</td>';
                   row += '<td>' + director + '</td>';
                   row += '<td>' + rating + '</td>';
                   row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + id +')">Edit</button></td>';
                   row += '<td><button type="button" class="btn btn-danger" onclick="deleteDvd(' + id + ')">Delete</button></td>';
                   row += '</tr>';

                 contentRows.append(row);
           })

       },
       error: function() {
         $('#errorMessages')
               .append($('<li>')
               .attr({class: 'list-group-item list-group-item-danger'})
               .text('Error calling web service.  Please try again later.'));
       }
     })
     })
 }
