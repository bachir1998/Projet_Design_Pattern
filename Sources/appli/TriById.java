package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TriById implements InterfaceTri {
    private Collection<People> _allpeople = null;
    private String _fileName;
    private String _startTime;
    private String _endTime;
    @Override
    public Collection<People> Tri(File file, String _start, String _stop) {

         /*
         csv file to read
         start time of the course
         end time of the source
        */
        this._startTime = _start;
        this._endTime = _stop;

        // load CSV file
        this._fileName = file.getName();
        var teamsFile = new TEAMSAttendanceList(file);

        // filter to extract data for each people
        var lines = teamsFile.get_attlist();
        if (lines != null) {
            // convert lines in data structure with people & periods
            var filter = new TEAMSAttendanceListAnalyzer(lines);
            // cut periods before start time and after end time
            filter.setStartAndStop(_start, _stop);
            // sort
            List<People> peopleByDuration = new ArrayList<>(filter.get_peopleList().values());
            Collections.sort(peopleByDuration, (one_id, other_id)->one_id.get_id().compareToIgnoreCase(other_id.get_id()));
            // init the people collection
            _allpeople = peopleByDuration;//filter.get_peopleList().values();


        }

        return _allpeople;

    }
}
