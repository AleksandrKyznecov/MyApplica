package com.example.myapplica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.programmerare.crsTransformations.CrsTransformationAdapter
import com.programmerare.crsTransformations.CrsTransformationResult;
import com.programmerare.crsTransformations.compositeTransformations.CrsTransformationAdapterCompositeFactory
import com.programmerare.crsTransformations.coordinate.createFromXEastingLongitudeAndYNorthingLatitude
import com.programmerare.crsTransformations.coordinate.latLon
import com.programmerare.crsTransformations.CrsTransformationResultStatistic
import com.programmerare.crsTransformations.CrsTransformationImplementationType
import com.programmerare.crsTransformations.crsIdentifier.CrsIdentifier
import com.programmerare.crsTransformations.crsIdentifier.createFromCrsCode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.txtview);
        var buttoncrs: Button = findViewById(R.id.button);
        buttoncrs.setOnClickListener(oclBtnOk);
    }
    val oclBtnOk: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            val textView: TextView = findViewById(R.id.txtview);
            val epsgWgs84 = 4326 //ESPG (European Petroleum Survey Group) world geo sistem стандартный код преобразования
            val epsgSweRef = 3006
            // alternative to the above hardcoding: use the library "crs-transformation-constants"
            // and constants EpsgNumber.WORLD__WGS_84__4326 and EpsgNumber.SWEDEN__SWEREF99_TM__3006
            // from the Java class com.programmerare.crsConstants.constantsByAreaNameNumber.v9_8_9.EpsgNumber
            val mgi = 4312;
            val ETRS89 = 4258;
            val crfi = equals(epsgWgs84.toString())
            textView.setText(crfi.toString());
            val centralStockholmWgs84 = createFromXEastingLongitudeAndYNorthingLatitude(59.330231, 18.059196, epsgWgs84)
            //val centralST = _createCrsTransformationResult.(59.330231, 18.059196, epsgWgs84)
            textView.setText(centralStockholmWgs84.toString());


            val austria = latLon(48.200800,16.368500, mgi)

            val crsTransformationAdapter = CrsTransformationAdapterCompositeFactory.createCrsTransformationMedian()
            // If the Gradle/Maven configuration includes all six adapter implementations, then the
            // above created 'Composite' implementation will below use all six 'leaf' implementations
            // and return a coordinate with a median longitude and a median latitude
            //val centralStockholmResultSweRef99TM = crsTransformationAdapter.transform(centralStockholmWgs84, epsgSweRef)
            val austriaETRS89 = crsTransformationAdapter.transform(austria, ETRS89)
            //if (centralStockholmResultSweRef99TM.isSuccess) {
                //textView.setText(centralStockholmResultSweRef99TM.outputCoordinate.toString());
                //textView.setText(austriaETRS89.outputCoordinate.toString());
                //textView.setText(austriaETRS89.toString());

                // Console output from the above code row:
                // Coordinate(xEastingLongitude=674032.3573263118, yNorthingLatitude=6580821.991123579, crsIdentifier=CrsIdentifier(crsCode='EPSG:3006', isEpsgCode=true, epsgNumber=3006))
            //}
        }
    }
}