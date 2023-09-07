package com.feny.pokemons.presenter

import com.feny.pokemons.model.Ability
import com.feny.pokemons.model.ApiResourceList
import com.feny.pokemons.model.Berry
import com.feny.pokemons.model.BerryFirmness
import com.feny.pokemons.model.BerryFlavor
import com.feny.pokemons.model.Characteristic
import com.feny.pokemons.model.ContestEffect
import com.feny.pokemons.model.ContestType
import com.feny.pokemons.model.EggGroup
import com.feny.pokemons.model.EncounterCondition
import com.feny.pokemons.model.EncounterConditionValue
import com.feny.pokemons.model.EncounterMethod
import com.feny.pokemons.model.EvolutionChain
import com.feny.pokemons.model.EvolutionTrigger
import com.feny.pokemons.model.Gender
import com.feny.pokemons.model.Generation
import com.feny.pokemons.model.GrowthRate
import com.feny.pokemons.model.Item
import com.feny.pokemons.model.ItemAttribute
import com.feny.pokemons.model.ItemCategory
import com.feny.pokemons.model.ItemFlingEffect
import com.feny.pokemons.model.ItemPocket
import com.feny.pokemons.model.Language
import com.feny.pokemons.model.Location
import com.feny.pokemons.model.LocationArea
import com.feny.pokemons.model.LocationAreaEncounter
import com.feny.pokemons.model.Machine
import com.feny.pokemons.model.Move
import com.feny.pokemons.model.MoveAilment
import com.feny.pokemons.model.MoveBattleStyle
import com.feny.pokemons.model.MoveCategory
import com.feny.pokemons.model.MoveDamageClass
import com.feny.pokemons.model.MoveLearnMethod
import com.feny.pokemons.model.MoveTarget
import com.feny.pokemons.model.NamedApiResourceList
import com.feny.pokemons.model.Nature
import com.feny.pokemons.model.PalParkArea
import com.feny.pokemons.model.PokeathlonStat
import com.feny.pokemons.model.Pokedex
import com.feny.pokemons.model.Pokemon
import com.feny.pokemons.model.PokemonColor
import com.feny.pokemons.model.PokemonForm
import com.feny.pokemons.model.PokemonHabitat
import com.feny.pokemons.model.PokemonShape
import com.feny.pokemons.model.PokemonSpecies
import com.feny.pokemons.model.Region
import com.feny.pokemons.model.Stat
import com.feny.pokemons.model.SuperContestEffect
import com.feny.pokemons.model.Type
import com.feny.pokemons.model.Version
import com.feny.pokemons.model.VersionGroup

interface PokeApi {

    fun getBerryList(offset: Int, limit: Int): NamedApiResourceList

    fun getBerryFirmnessList(offset: Int, limit: Int): NamedApiResourceList

    fun getBerryFlavorList(offset: Int, limit: Int): NamedApiResourceList

    fun getContestTypeList(offset: Int, limit: Int): NamedApiResourceList

    fun getContestEffectList(offset: Int, limit: Int): ApiResourceList

    fun getSuperContestEffectList(offset: Int, limit: Int): ApiResourceList

    fun getEncounterMethodList(offset: Int, limit: Int): NamedApiResourceList

    fun getEncounterConditionList(offset: Int, limit: Int): NamedApiResourceList

    fun getEncounterConditionValueList(offset: Int, limit: Int): NamedApiResourceList

    fun getEvolutionChainList(offset: Int, limit: Int): ApiResourceList

    fun getEvolutionTriggerList(offset: Int, limit: Int): NamedApiResourceList

    fun getGenerationList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokedexList(offset: Int, limit: Int): NamedApiResourceList

    fun getVersionList(offset: Int, limit: Int): NamedApiResourceList

    fun getVersionGroupList(offset: Int, limit: Int): NamedApiResourceList

    fun getItemList(offset: Int, limit: Int): NamedApiResourceList

    fun getItemAttributeList(offset: Int, limit: Int): NamedApiResourceList

    fun getItemCategoryList(offset: Int, limit: Int): NamedApiResourceList

    fun getItemFlingEffectList(offset: Int, limit: Int): NamedApiResourceList

    fun getItemPocketList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveAilmentList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveBattleStyleList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveCategoryList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveDamageClassList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveLearnMethodList(offset: Int, limit: Int): NamedApiResourceList

    fun getMoveTargetList(offset: Int, limit: Int): NamedApiResourceList

    fun getLocationList(offset: Int, limit: Int): NamedApiResourceList

    fun getLocationAreaList(offset: Int, limit: Int): NamedApiResourceList

    fun getPalParkAreaList(offset: Int, limit: Int): NamedApiResourceList

    fun getRegionList(offset: Int, limit: Int): NamedApiResourceList

    fun getMachineList(offset: Int, limit: Int): ApiResourceList

    fun getAbilityList(offset: Int, limit: Int): NamedApiResourceList

    fun getCharacteristicList(offset: Int, limit: Int): ApiResourceList

    fun getEggGroupList(offset: Int, limit: Int): NamedApiResourceList

    fun getGenderList(offset: Int, limit: Int): NamedApiResourceList

    fun getGrowthRateList(offset: Int, limit: Int): NamedApiResourceList

    fun getNatureList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokeathlonStatList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonColorList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonFormList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonHabitatList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonShapeList(offset: Int, limit: Int): NamedApiResourceList

    fun getPokemonSpeciesList(offset: Int, limit: Int): NamedApiResourceList

    fun getStatList(offset: Int, limit: Int): NamedApiResourceList

    fun getTypeList(offset: Int, limit: Int): NamedApiResourceList

    fun getLanguageList(offset: Int, limit: Int): NamedApiResourceList

    fun getBerry(id: Int): Berry

    fun getBerryFirmness(id: Int): BerryFirmness

    fun getBerryFlavor(id: Int): BerryFlavor

    fun getContestType(id: Int): ContestType

    fun getContestEffect(id: Int): ContestEffect

    fun getSuperContestEffect(id: Int): SuperContestEffect

    fun getEncounterMethod(id: Int): EncounterMethod

    fun getEncounterCondition(id: Int): EncounterCondition

    fun getEncounterConditionValue(id: Int): EncounterConditionValue

    fun getEvolutionChain(id: Int): EvolutionChain

    fun getEvolutionTrigger(id: Int): EvolutionTrigger

    fun getGeneration(id: Int): Generation

    fun getPokedex(id: Int): Pokedex

    fun getVersion(id: Int): Version

    fun getVersionGroup(id: Int): VersionGroup

    fun getItem(id: Int): Item

    fun getItemAttribute(id: Int): ItemAttribute

    fun getItemCategory(id: Int): ItemCategory

    fun getItemFlingEffect(id: Int): ItemFlingEffect

    fun getItemPocket(id: Int): ItemPocket

    fun getMove(id: Int): Move

    fun getMoveAilment(id: Int): MoveAilment

    fun getMoveBattleStyle(id: Int): MoveBattleStyle

    fun getMoveCategory(id: Int): MoveCategory

    fun getMoveDamageClass(id: Int): MoveDamageClass

    fun getMoveLearnMethod(id: Int): MoveLearnMethod

    fun getMoveTarget(id: Int): MoveTarget

    fun getLocation(id: Int): Location

    fun getLocationArea(id: Int): LocationArea

    fun getPalParkArea(id: Int): PalParkArea

    fun getRegion(id: Int): Region

    fun getMachine(id: Int): Machine

    fun getAbility(id: Int): Ability

    fun getCharacteristic(id: Int): Characteristic

    fun getEggGroup(id: Int): EggGroup

    fun getGender(id: Int): Gender

    fun getGrowthRate(id: Int): GrowthRate

    fun getNature(id: Int): Nature

    fun getPokeathlonStat(id: Int): PokeathlonStat

    fun getPokemon(id: Int): Pokemon

    fun getPokemonEncounterList(id: Int): List<LocationAreaEncounter>

    fun getPokemonColor(id: Int): PokemonColor

    fun getPokemonForm(id: Int): PokemonForm

    fun getPokemonHabitat(id: Int): PokemonHabitat

    fun getPokemonShape(id: Int): PokemonShape

    fun getPokemonSpecies(id: Int): PokemonSpecies

    fun getStat(id: Int): Stat

    fun getType(id: Int): Type

    fun getLanguage(id: Int): Language
}
