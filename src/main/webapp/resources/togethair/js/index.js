jQuery(document).ready(function($) {
	$('#illustration')
        .click(function() {
            $(this).addClass('expand');
        }).mouseleave(function() {
            $(this).removeClass('expand');
        });

    $(document).ready(function() {
        function showReturnDate() {
            var date = $('#departureDate .datepicker').datepicker('getDate');
            date.setDate(date.getDate() + 1);

            $('#returnDate').show().find('.datepicker').datepicker("setDate", date);
            $('#departureDate').removeClass('col-md-6').addClass('col-md-3');
        }

        function hideReturnDate() {
            $('#returnDate').hide();
            $('#departureDate').removeClass('col-md-3').addClass('col-md-6').find('.datepicker').datepicker('option', 'maxDate', null);
        }

        $('#searchForm\\:ticketType\\:0').change(hideReturnDate);
        $('#searchForm\\:ticketType\\:1').change(showReturnDate);

        $('#searchForm\\:ticketType input[type="radio"]:checked').change();

        $('#departureDate .datepicker').datepicker({
            minDate : 0,
            dateFormat: 'dd/mm/yy',
            onSelect: function(selectedDate) {
                $('#returnDate .datepicker').datepicker('option', 'minDate', selectedDate);
            }
        }).datepicker("setDate", new Date());

        $('#returnDate .datepicker').datepicker({
			minDate : 0,
            dateFormat: 'dd/mm/yy',
            onSelect: function( selectedDate ) {
                $('#departureDate .datepicker').datepicker('option', 'maxDate', selectedDate);
            }
        });
    });
});